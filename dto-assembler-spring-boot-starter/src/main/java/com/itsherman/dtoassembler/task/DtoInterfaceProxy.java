package com.itsherman.dtoassembler.task;


import com.itsherman.dtoassembler.constants.Commonconstants;
import com.itsherman.dtoassembler.core.*;
import com.itsherman.dtoassembler.exception.DtoAssembleException;
import com.itsherman.dtoassembler.pool.DtoViewDefinitionPool;
import com.itsherman.dtoassembler.utils.DtoPropertyAssembleUtils;
import com.itsherman.dtoassembler.utils.SpringUtils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DtoInterfaceProxy<T, R> implements InvocationHandler {

    private DtoViewDefinition dvd;

    private T[] sources;

    public R getInstance(Class<R> dtoClass, Class<?> viewCLass, T... sources) {
        Class<?>[] interfaces = dtoClass.getInterfaces();
        Class<?>[] finalInterfaces = new Class[interfaces.length + 1];
        finalInterfaces[0] = dtoClass;
        for (int i = 0; i < interfaces.length; i++) {
            finalInterfaces[i + 1] = interfaces[i];
        }
        DtoViewDefinitionPool pool = SpringUtils.getBean("dtoViewDefinitionPool", DtoViewDefinitionPool.class);
        DtoViewDefinition dvd = pool.getViewDefinition(dtoClass, viewCLass);
        if (dvd == null) {
            throw new DtoAssembleException(String.format("Can not found any dtoDefinition for dtoClass '%s'", dtoClass.getName()));
        }
        this.dvd = dvd;
        this.sources = sources;
        return (R) Proxy.newProxyInstance(dtoClass.getClassLoader(), finalInterfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if (method.getName().startsWith(Commonconstants.GETTER_PREFIX)) {
            DtoModelDefinition md = dvd.getMd();
            List<ModelPropertyDefinition> mpds = md.getMpds();
            for (ModelPropertyDefinition mpd : mpds) {
                DtoInterPropertyDefinition propertyDefinition = (DtoInterPropertyDefinition) mpd;
                List<Method> dtoMethods = dvd.getVpds().stream().map(vpd -> {
                    InterViewPropertyDefinition ivpd = (InterViewPropertyDefinition) vpd;
                    return ivpd.getDtoMethod();
                }).collect(Collectors.toList());
                if (dtoMethods.contains(method)) {
                    if (propertyDefinition.getDtoMethod().equals(method)) {
                        if (method.isDefault()) {
                            Class<?> declaringClass = method.getDeclaringClass();
                            Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
                            constructor.setAccessible(true);
                            result = constructor
                                    .newInstance(declaringClass, MethodHandles.Lookup.PRIVATE)
                                    .unreflectSpecial(method, declaringClass)
                                    .bindTo(proxy)
                                    .invokeWithArguments(args);
                        } else {
                            Optional<?> sourceOptional = Arrays.stream(sources).filter(t -> propertyDefinition.getSourceClass().isAssignableFrom(t.getClass())).findFirst();
                            Method readMethod = propertyDefinition.getSourceReadMethod();
                            Object readMethodValue;
                            if (sourceOptional.isPresent()) {
                                readMethodValue = readMethod.invoke(sourceOptional.get(), args);
                            } else {
                                throw new DtoAssembleException(String.format("Illegal Source Object,it required sourceClass '%s',but not found", propertyDefinition.getSourceClass()));
                            }
                            if (readMethodValue != null) {
                                Type returnType = propertyDefinition.getDtoMethod().getGenericReturnType();
                                try {
                                    result = DtoPropertyAssembleUtils.doAssemble(returnType, readMethodValue);
                                } catch (RuntimeException e) {
                                    throw new DtoAssembleException(String.format("无法编集 %s 到 %s。 目标类型：%s, 原因：%s", readMethod.getName(), propertyDefinition.getDtoMethod().getName(), md.getDtoClass().getSimpleName(), e.getMessage()), e);
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
        }

        if (method.getDeclaringClass().isAssignableFrom(Object.class)) {
            return method.invoke(this, args);
        }
        if (result == null) {
            if (method.getReturnType().isAssignableFrom(Void.class)) {
                return Void.class.newInstance();
            }
        }
        return result;
    }
}
