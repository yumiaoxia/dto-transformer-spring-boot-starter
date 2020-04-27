package com.itsherman.dtoassembler.handler.clazz;

import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtoassembler.core.DtoClassPropertyDefinition;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

public class DtoClassPropertyParserClassHandler {


    public void handle(DtoClassPropertyDefinition dcpd) {
        dcpd.getParserClass().add(Void.class);
        Method readMethod = dcpd.getDtoReadMethod();
        if (readMethod != null) {
            DtoView dtoView = readMethod.getAnnotation(DtoView.class);
            if (dtoView != null) {
                Class<?>[] parserClasses = dtoView.viewClasses();
                Collections.addAll(dcpd.getParserClass(), parserClasses);
                String[] referenceFieldNames = dtoView.referenceNames();
                dcpd.setReferenceFieldNames(referenceFieldNames);
            }
            Field field = dcpd.getDtoField();
            if (field != null) {
                dtoView = field.getAnnotation(DtoView.class);
                if (dtoView != null) {
                    Class<?>[] parserClasses = dtoView.viewClasses();
                    Collections.addAll(dcpd.getParserClass(), parserClasses);
                }
            }
        } else {
            Field field = dcpd.getDtoField();
            if (field != null) {
                DtoView dtoView = field.getAnnotation(DtoView.class);
                if (dtoView != null) {
                    Class<?>[] parserClasses = dtoView.viewClasses();
                    Collections.addAll(dcpd.getParserClass(), parserClasses);
                }
            }
        }

    }
}
