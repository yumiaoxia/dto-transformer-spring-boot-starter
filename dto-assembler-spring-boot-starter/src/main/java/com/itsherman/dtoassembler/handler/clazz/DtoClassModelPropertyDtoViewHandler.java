package com.itsherman.dtoassembler.handler.clazz;


import com.itsherman.dtoassembler.annotations.ViewSelector;
import com.itsherman.dtoassembler.core.DtoClassPropertyDefinition;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DtoClassModelPropertyDtoViewHandler {

    public void handle(DtoClassPropertyDefinition dcpd) {
        Method dtoWriteMethod = dcpd.getDtoWriteMethod();
        Class<?> viewClass = Void.class;
        if (dtoWriteMethod != null) {
            ViewSelector viewSelector = dtoWriteMethod.getAnnotation(ViewSelector.class);
            if (viewSelector != null) {
                viewClass = viewSelector.selectView();
            }
            Field field = dcpd.getDtoField();
            if (field != null) {
                viewSelector = field.getAnnotation(ViewSelector.class);
                if (viewSelector != null) {
                    viewClass = viewSelector.selectView();
                }
            }
        } else {
            Field field = dcpd.getDtoField();
            if (field != null) {
                ViewSelector viewSelector = field.getAnnotation(ViewSelector.class);
                if (viewSelector != null) {
                    viewClass = viewSelector.selectView();
                }
            }
        }
        dcpd.setViewClass(viewClass);

    }
}
