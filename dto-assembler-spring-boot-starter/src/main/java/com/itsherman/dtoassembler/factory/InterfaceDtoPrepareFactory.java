package com.itsherman.dtoassembler.factory;


import com.itsherman.dtoassembler.handler.DtoModelRegister;
import com.itsherman.dtoassembler.handler.DtoViewRegister;
import com.itsherman.dtoassembler.handler.inter.DtoInterViewRegister;
import com.itsherman.dtoassembler.handler.inter.InterDtoModelRegister;

import javax.annotation.Resource;

public class InterfaceDtoPrepareFactory extends DtoPrepareFactory {

    @Resource
    private InterDtoModelRegister dtoModelRegister;


    @Resource
    private DtoInterViewRegister dtoInterViewRegister;


    @Override
    public DtoModelRegister getDtoModelRegister() {
        return dtoModelRegister;
    }

    @Override
    public DtoViewRegister getDtoViewRegister() {
        return dtoInterViewRegister;
    }

}
