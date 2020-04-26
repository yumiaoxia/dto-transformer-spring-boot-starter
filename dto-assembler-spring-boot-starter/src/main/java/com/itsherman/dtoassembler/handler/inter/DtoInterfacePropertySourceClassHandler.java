package com.itsherman.dtoassembler.handler.inter;

import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtoassembler.constants.Commonconstants;
import com.itsherman.dtoassembler.core.DtoInterPropertyDefinition;
import com.itsherman.dtoassembler.exception.DtoPrepareException;

import java.lang.reflect.Method;
import java.util.List;

public class DtoInterfacePropertySourceClassHandler {

    public void handle(DtoInterPropertyDefinition ipd) {
        Method dtoMethod = ipd.getDtoMethod();
        if (!dtoMethod.isDefault()) {
            DtoProperty dtoProperty = dtoMethod.getAnnotation(DtoProperty.class);
            List<Class<?>> fromClasses = ipd.getMd().getFromClasses();
            String value = ipd.getValue();
            String expectReadMethodName = Commonconstants.GETTER_PREFIX + value.substring(0, 1).toUpperCase() + value.substring(1);
            if (dtoProperty != null) {
                Class<?> sourceClass = dtoProperty.sourceClass();
                if (sourceClass.equals(Void.class)) {
                    loopFromClass(fromClasses, value, ipd);
                } else {
                    if (!fromClasses.contains(sourceClass)) {
                        throw new DtoPrepareException(String.format("DtoModel(%s) can not found any fromClass which mapping sourceClass on property(%s)", ipd.getMd().getDtoClass().getSimpleName(), ipd.getValue()));
                    }
                    try {
                        sourceClass.getMethod(expectReadMethodName);
                        ipd.setSourceClass(sourceClass);
                    } catch (NoSuchMethodException e) {
                        throw new DtoPrepareException(String.format("Model(%s) can not found readMethod(%s）in sourceClass(%s)", ipd.getMd().getDtoClass().getSimpleName(), expectReadMethodName, sourceClass.getSimpleName()));
                    }
                }
            } else {
                loopFromClass(fromClasses, value, ipd);
            }
        }
    }

    private void loopFromClass(List<Class<?>> fromClasses, String value, DtoInterPropertyDefinition ipd) {
        boolean flag = false;
        String expectReadMethodName = Commonconstants.GETTER_PREFIX + value.substring(0, 1).toUpperCase() + value.substring(1);
        for (Class<?> fromClass : fromClasses) {
            try {
                Method sourceReadMethod = fromClass.getMethod(expectReadMethodName);
                flag = true;
                ipd.setSourceClass(fromClass);
                ipd.setSourceReadMethod(sourceReadMethod);
                break;
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
        if (!flag) {
            throw new DtoPrepareException(String.format("can not found readMethod mapping dtoProperty(%s) in model(%s)", value, ipd.getMd().getDtoClass().getSimpleName()));
        }
    }
}
