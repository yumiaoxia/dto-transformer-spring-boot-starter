package com.itsherman.dtoassembler.handler.clazz;


import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtoassembler.core.DtoClassPropertyDefinition;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DtoClassModelPropertyDtoViewHandler {

    public void handle(DtoClassPropertyDefinition dcpd) {
        Method dtoWriteMethod = dcpd.getDtoWriteMethod();
        Class<?> viewClass = Void.class;
        if (dtoWriteMethod != null) {
            DtoView dtoView = dtoWriteMethod.getAnnotation(DtoView.class);
            if (dtoView != null) {
                viewClass = dtoView.viewClass();
            }
            Field field = dcpd.getDtoField();
            if (field != null) {
                dtoView = field.getAnnotation(DtoView.class);
                if (dtoView != null) {
                    viewClass = dtoView.viewClass();
                }
            }
        } else {
            Field field = dcpd.getDtoField();
            if (field != null) {
                DtoView dtoView = field.getAnnotation(DtoView.class);
                if (dtoView != null) {
                    viewClass = dtoView.viewClass();
                }
            }
        }
        dcpd.setViewClass(viewClass);

    }
}
