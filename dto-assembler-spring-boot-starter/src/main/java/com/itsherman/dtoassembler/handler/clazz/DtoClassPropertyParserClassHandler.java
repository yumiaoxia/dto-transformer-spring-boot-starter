package com.itsherman.dtoassembler.handler.clazz;

import com.itsherman.dtoassembler.annotations.ViewParser;
import com.itsherman.dtoassembler.core.DtoClassPropertyDefinition;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

public class DtoClassPropertyParserClassHandler {


    public void handle(DtoClassPropertyDefinition dcpd) {
        dcpd.getParserClass().add(Void.class);
        Method readMethod = dcpd.getDtoReadMethod();
        if (readMethod != null) {
            ViewParser viewParser = readMethod.getAnnotation(ViewParser.class);
            if (viewParser != null) {
                Class<?>[] parserClasses = viewParser.parserClasses();
                Collections.addAll(dcpd.getParserClass(), parserClasses);
            }
            Field field = dcpd.getDtoField();
            if (field != null) {
                viewParser = field.getAnnotation(ViewParser.class);
                if (viewParser != null) {
                    Class<?>[] parserClasses = viewParser.parserClasses();
                    Collections.addAll(dcpd.getParserClass(), parserClasses);
                }
            } else {
                String[] referenceFieldNames = viewParser.referenceFieldNames();
                dcpd.setReferenceFieldNames(referenceFieldNames);
            }
        } else {
            Field field = dcpd.getDtoField();
            if (field != null) {
                ViewParser viewParser = field.getAnnotation(ViewParser.class);
                if (viewParser != null) {
                    Class<?>[] parserClasses = viewParser.parserClasses();
                    Collections.addAll(dcpd.getParserClass(), parserClasses);
                }
            }
        }

    }
}
