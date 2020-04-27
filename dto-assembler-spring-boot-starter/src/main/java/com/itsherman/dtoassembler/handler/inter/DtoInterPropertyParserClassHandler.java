package com.itsherman.dtoassembler.handler.inter;

import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtoassembler.core.DtoInterPropertyDefinition;

import java.lang.reflect.Method;
import java.util.Collections;

public class DtoInterPropertyParserClassHandler {

    public void handle(DtoInterPropertyDefinition ipd) {
        Method dtoMethod = ipd.getDtoMethod();
        ipd.getParserClass().add(Void.class);
        DtoView dtoView = dtoMethod.getAnnotation(DtoView.class);
        if (dtoView != null) {
            Class<?>[] parserClasses = dtoView.viewClasses();
            Collections.addAll(ipd.getParserClass(), parserClasses);
            String[] referenceNames = dtoView.referenceNames();
            if (referenceNames.length > 0) {
                ipd.setReferenceDtoMethodNames(referenceNames);
            }
        }
    }
}
