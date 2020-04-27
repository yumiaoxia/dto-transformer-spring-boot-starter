package com.itsherman.dtoassembler.handler.inter;

import com.itsherman.dtoassembler.annotations.ViewParser;
import com.itsherman.dtoassembler.core.DtoInterPropertyDefinition;

import java.lang.reflect.Method;
import java.util.Collections;

public class DtoInterPropertyParserClassHandler {

    public void handle(DtoInterPropertyDefinition ipd) {
        Method dtoMethod = ipd.getDtoMethod();
        ipd.getParserClass().add(Void.class);
        ViewParser viewParser = dtoMethod.getAnnotation(ViewParser.class);
        if (viewParser != null) {
            Class<?>[] parserClasses = viewParser.parserClasses();
            Collections.addAll(ipd.getParserClass(), parserClasses);
            String[] referenceNames = viewParser.referenceFieldNames();
            if (referenceNames.length > 0) {
                ipd.setReferenceDtoMethodNames(referenceNames);
            }
        }
    }
}
