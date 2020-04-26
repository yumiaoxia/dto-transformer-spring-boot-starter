package com.itsherman.dtoassembler.handler.clazz;


import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtoassembler.constants.Commonconstants;
import com.itsherman.dtoassembler.core.DtoClassPropertyDefinition;
import com.itsherman.dtoassembler.exception.DtoPrepareException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.List;

public class DtoClassPropertySourceClassHandler {

    public void handle(DtoClassPropertyDefinition dcpd) {
        Method dtoWriteMethod = dcpd.getDtoWriteMethod();
        List<Class<?>> fromClasses = dcpd.getMd().getFromClasses();
        Field field = dcpd.getDtoField();
        String value = dcpd.getValue();
        if (dtoWriteMethod != null) {
            DtoProperty dtoProperty = dtoWriteMethod.getAnnotation(DtoProperty.class);
            if (dtoProperty != null) {
                handledDtoProperty(dtoProperty, fromClasses, dtoWriteMethod, value, dcpd);
            } else {
                if (field != null) {
                    dtoProperty = field.getAnnotation(DtoProperty.class);
                    if (dtoProperty == null) {
                        loopFromClass(fromClasses, dtoWriteMethod, value, dcpd);
                    } else {
                        handledDtoProperty(dtoProperty, fromClasses, dtoWriteMethod, value, dcpd);
                    }
                } else {
                    loopFromClass(fromClasses, dtoWriteMethod, value, dcpd);
                }
            }
        }
    }

    private void handledDtoProperty(DtoProperty dtoProperty, List<Class<?>> fromClasses, Method dtoWriteMethod, String value, DtoClassPropertyDefinition cpd) {
        Class<?> sourceClass = dtoProperty.sourceClass();
        if (!sourceClass.isAssignableFrom(Void.class) && !fromClasses.contains(sourceClass)) {
            throw new DtoPrepareException(String.format("DtoModel(%s) can not found any fromClass which mapping sourceClass on property(%s)", cpd.getMd().getDtoClass().getSimpleName(), cpd.getValue()));
        }
        String readMethodName = Commonconstants.GETTER_PREFIX + value.substring(0, 1).toUpperCase() + value.substring(1);
        if (sourceClass.equals(Void.class)) {
            loopFromClass(fromClasses, dtoWriteMethod, value, cpd);
        } else {
            Method readMethod;
            try {
                readMethod = sourceClass.getMethod(readMethodName);
            } catch (NoSuchMethodException e) {
                throw new DtoPrepareException(String.format("can not found readMethod mapping dtoProperty(%s) in model(%s)", value, cpd.getMd().getDtoClass().getSimpleName()), e);
            }
        }
    }


    private void loopFromClass(List<Class<?>> fromClasses, Method dtoWriteMethod, String value, DtoClassPropertyDefinition cpd) {
        boolean flag = false;
        String readMethodName = Commonconstants.GETTER_PREFIX + value.substring(0, 1).toUpperCase() + value.substring(1);
        for (Class<?> fromClass : fromClasses) {
            Parameter parameter = dtoWriteMethod.getParameters()[0];
            Type parameterizedType = parameter.getParameterizedType();
            Method method;
            try {
                method = fromClass.getMethod(readMethodName);
            } catch (NoSuchMethodException e) {
                continue;
            }
            if (method != null) {
                flag = true;
                cpd.setSourceClass(fromClass);
                cpd.setSourceReadMethod(method);
            }
        }
        if (!flag) {
            throw new DtoPrepareException(String.format("can not found readMethod mapping dtoProperty(%s) in model(%s)", value, cpd.getMd().getDtoClass().getSimpleName()));
        }
    }
}
