package com.itsherman.dtoassembler.handler;

import com.itsherman.dtoassembler.core.DtoModelDefinition;
import com.itsherman.dtoassembler.factory.ClassDtoPrepareFactory;
import com.itsherman.dtoassembler.factory.DtoPrepareFactory;
import com.itsherman.dtoassembler.factory.InterfaceDtoPrepareFactory;
import com.itsherman.dtoassembler.pool.DtoClassesHolder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class DtoModelDispatchHandler {

    @Autowired
    private InterfaceDtoPrepareFactory interfaceDtoPrepareFactory;

    @Autowired
    private ClassDtoPrepareFactory classDtoPrepareFactory;

    public void handle() {
        Set<Class<?>> dtoClasses = DtoClassesHolder.getInstance().getDtoClasses();
        for (Class<?> dtoClass : dtoClasses) {
            DtoPrepareFactory dtoPrerareFactory;
            if (dtoClass.isInterface()) {
                dtoPrerareFactory = interfaceDtoPrepareFactory;
            } else {
                dtoPrerareFactory = classDtoPrepareFactory;
            }
            DtoModelDefinition md = dtoPrerareFactory.getDtoModelRegister().register(dtoClass);
            DtoViewRegister dtoViewRegister = dtoPrerareFactory.getDtoViewRegister();
            dtoViewRegister.register(md);
        }
    }
}
