package com.itsherman.dtoassembler.handler.clazz;

import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtoassembler.core.DtoClassPropertyDefinition;
import com.itsherman.dtoassembler.exception.DtoPrepareException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DtoClassPropertyValueHandler {

    public void handle(DtoClassPropertyDefinition dcpd) {
        String expectFieldName = dcpd.getExpectFieldName();
        dcpd.setValue(dcpd.getExpectFieldName());
        Method dtoWriteMethod = dcpd.getDtoWriteMethod();
        Field field = dcpd.getDtoField();
        if (dtoWriteMethod != null) {
            DtoProperty dtoProperty = dtoWriteMethod.getAnnotation(DtoProperty.class);
            if (dtoProperty != null) {
                String value = dtoProperty.value();
                dcpd.setValue(value.equals("") ? expectFieldName : value);
                if (field != null) {
                    dtoProperty = field.getAnnotation(DtoProperty.class);
                    if (dtoProperty != null) {
                        throw new DtoPrepareException(String.format("Duplicated annotation on field or method,fieldName: %s", field.getName()));
                    }
                }
            } else {
                if (field != null) {
                    dtoProperty = field.getAnnotation(DtoProperty.class);
                    if (dtoProperty != null) {
                        String value = dtoProperty.value();
                        dcpd.setValue(value.equals("") ? expectFieldName : value);
                    }
                }
            }
        } else {
            if (field != null) {
                DtoProperty dtoProperty = field.getAnnotation(DtoProperty.class);
                if (dtoProperty != null) {
                    String value = dtoProperty.value();
                    dcpd.setValue(value.equals("") ? expectFieldName : value);
                }
            }
        }

    }

}
