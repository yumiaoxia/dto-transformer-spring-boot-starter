package com.itsherman.dtoassembler.factory;

import com.itsherman.dtoassembler.handler.DtoModelRegister;
import com.itsherman.dtoassembler.handler.DtoViewRegister;
import com.itsherman.dtoassembler.utils.SpringUtils;

public abstract class DtoPrepareFactory {


    public static DtoPrepareFactory of(Class<?> dtoClass) {
        DtoPrepareFactory dtoPrepareFactory = null;
        if (dtoClass.isInterface()) {
            dtoPrepareFactory = SpringUtils.getBean("interfaceDtoPrepareFactory", InterfaceDtoPrepareFactory.class);
        } else {
            dtoPrepareFactory = SpringUtils.getBean("classDtoPrepareFactory", ClassDtoPrepareFactory.class);
        }
        return dtoPrepareFactory;
    }


    public abstract DtoModelRegister getDtoModelRegister();

    public abstract DtoViewRegister getDtoViewRegister();
}
