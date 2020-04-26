package com.itsherman.dtoassembler.handler.inter;

import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtoassembler.core.DtoInterPropertyDefinition;

import java.lang.reflect.Method;

public class DtoInterModelPropertyDtoViewHandler {

    public void handle(DtoInterPropertyDefinition ipd) {
        ipd.setViewCLass(Void.class);
        Method dtoMethod = ipd.getDtoMethod();
        DtoView dtoView = dtoMethod.getAnnotation(DtoView.class);
        if (dtoView != null) {
            Class<?> viewClass = dtoView.viewClass();
            ipd.setViewCLass(viewClass);
        }
    }
}
