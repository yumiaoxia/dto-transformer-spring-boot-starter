package com.itsherman.dtoassembler.handler;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.core.DtoModelDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DtoModelRegister {

    public DtoModelDefinition register(Class<?> dtoClass) {
        DtoModelDefinition md = new DtoModelDefinition();
        md.setDtoClass(dtoClass);
        DtoModel dtoModel = dtoClass.getAnnotation(DtoModel.class);
        List<Class<?>> fromClasses = new ArrayList<>();
        Collections.addAll(fromClasses, dtoModel.from());
        md.setFromClasses(fromClasses);
        doRegister(md);
        return md;
    }


    public abstract void doRegister(DtoModelDefinition md);
}
