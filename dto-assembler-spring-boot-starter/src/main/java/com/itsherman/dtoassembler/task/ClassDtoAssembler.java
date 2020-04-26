package com.itsherman.dtoassembler.task;

import com.itsherman.dtoassembler.core.*;
import com.itsherman.dtoassembler.exception.DtoAssembleException;
import com.itsherman.dtoassembler.pool.DtoViewDefinitionPool;
import com.itsherman.dtoassembler.utils.CollectionUtils;
import com.itsherman.dtoassembler.utils.DtoAssembleUtils;
import com.itsherman.dtoassembler.utils.SpringUtils;

import java.lang.reflect.*;
import java.util.*;

public class ClassDtoAssembler<T, R> implements DtoAssembler<T, R> {

    @SafeVarargs
    @Override
    public final R assemble(Class<R> dtoClass, Class<?> viewClass, T... ts) {
        R r;
        try {
            r = dtoClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new DtoAssembleException();
        }
        DtoViewDefinitionPool dtoViewDefinitionPool = SpringUtils.getBean("dtoViewDefinitionPool", DtoViewDefinitionPool.class);
        DtoViewDefinition viewDefinition = dtoViewDefinitionPool.getViewDefinition(dtoClass, viewClass);
        if (viewDefinition == null) {
            throw new DtoAssembleException(String.format("Can not found any dtoDefinition for dtoClass '%s'", dtoClass.getName()));
        }
        DtoModelDefinition md = viewDefinition.getMd();
        List<ModelPropertyDefinition> modelProperties = md.getMpds();
        for (ModelPropertyDefinition mp : modelProperties) {
            DtoClassPropertyDefinition cpd = (DtoClassPropertyDefinition) mp;
            List<Field> fields = new ArrayList<>();
            for (ViewPropertyDefinition vpd : viewDefinition.getVpds()) {
                ClassViewPropertyDefinition cvpd = (ClassViewPropertyDefinition) vpd;
                fields.addAll(cvpd.getFields());
            }
            if (fields.contains(((DtoClassPropertyDefinition) mp).getDtoField())) {
                Optional<T> first = Arrays.stream(ts).filter(t -> cpd.getSourceClass().isAssignableFrom(t.getClass())).findFirst();
                if (!first.isPresent()) {
                    throw new DtoAssembleException(String.format("Illegal Source Object,it required sourceClass '%s',but not found", cpd.getSourceClass()));
                }
                Method dtoWriteMethod = cpd.getDtoWriteMethod();
                Parameter parameter = dtoWriteMethod.getParameters()[0];
                Type writeMethodParameterType = parameter.getParameterizedType();

                Method readMethod = cpd.getSourceReadMethod();
                Object readMethodValue;
                try {
                    readMethodValue = readMethod.invoke(first.get());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new DtoAssembleException(e);
                }
                Object writeMethodValue;
                try {
                    if (readMethodValue != null) {
                        writeMethodValue = doAssemble(writeMethodParameterType, readMethodValue, cpd.getViewClass());
                        if (writeMethodValue != null) {
                            dtoWriteMethod.invoke(r, writeMethodValue);
                        }
                    }
                } catch (RuntimeException | InvocationTargetException | IllegalAccessException e) {
                    throw new DtoAssembleException(String.format("无法编集 %s。 目标类型：%s, 原因：%s", cpd.getDtoField().getName(), dtoClass, e.getMessage()), e);
                }
            }
        }
        return r;
    }

    private Object doAssemble(Type writeMethodParameterType, Object sourceReturnValue, Class<?> viewClass) {
        if (writeMethodParameterType instanceof Class) {
            Class<?> parameterClass = (Class) writeMethodParameterType;
            // 如果参数类型是数组
            if (parameterClass.isArray()) {
                Class<?> componentType = parameterClass.getComponentType();
                Object[] readMethodValues = (Object[]) sourceReturnValue;
                Object writeMethodParameterValues = Array.newInstance(componentType, readMethodValues.length);
                for (int i = 0; i < readMethodValues.length; i++) {
                    Object readMethodValue = readMethodValues[i];
                    Array.set(writeMethodParameterValues, i, readMethodValue == null ? null : doAssemble(componentType, readMethodValue, viewClass));
                }
                return writeMethodParameterValues;
                // 如果参数类型是基本类型
            } else if (parameterClass.isPrimitive() || Modifier.isFinal(parameterClass.getModifiers())) {
                return sourceReturnValue;
                // 如果参数类型是引用类型
            } else {
                DtoViewDefinitionPool pool = SpringUtils.getBean("dtoViewDefinitionPool", DtoViewDefinitionPool.class);
                return sourceReturnValue == null ? null : DtoAssembleUtils.assemble(parameterClass, viewClass, sourceReturnValue);
            }
            // 如果参数类型是参数化类型
        } else if (writeMethodParameterType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) writeMethodParameterType;
            Type rawType = parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (rawType instanceof Class) {
                Class<?> rawClass = (Class) rawType;
                if (Collection.class.isAssignableFrom(rawClass)) {
                    Collection<Object> sourceValues = (Collection) sourceReturnValue;
                    Collection<Object> parameterValues = CollectionUtils.emptyCollection(parameterizedType);
                    for (Object sourceValue : sourceValues) {
                        if (sourceValue != null) {
                            Object parameterVal = doAssemble(actualTypeArguments[0], sourceValue, viewClass);
                            parameterValues.add(parameterVal);
                        }
                    }
                    return parameterValues;
                }
            }
        }
        throw new DtoAssembleException(String.format("Type match Error,target type '%s'", writeMethodParameterType));
    }
}
