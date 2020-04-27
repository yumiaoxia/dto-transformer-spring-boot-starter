package com.itsherman.dtoassembler.handler.inter;

import com.itsherman.dtoassembler.annotations.ViewSelector;
import com.itsherman.dtoassembler.core.DtoInterPropertyDefinition;

import java.lang.reflect.Method;

public class DtoInterModelPropertyDtoViewHandler {

    public void handle(DtoInterPropertyDefinition ipd) {
        ipd.setViewClass(Void.class);
        Method dtoMethod = ipd.getDtoMethod();
        ViewSelector viewSelector = dtoMethod.getAnnotation(ViewSelector.class);
        if (viewSelector != null) {
            Class<?> viewClass = viewSelector.selectView();
            ipd.setViewClass(viewClass);
        }
    }
}
