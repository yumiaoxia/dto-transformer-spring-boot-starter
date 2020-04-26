package com.itsherman.dtoassembler.factory;


import com.itsherman.dtoassembler.handler.DtoModelRegister;
import com.itsherman.dtoassembler.handler.DtoViewRegister;
import com.itsherman.dtoassembler.handler.clazz.ClassDtoModelRegister;
import com.itsherman.dtoassembler.handler.clazz.DtoClassViewRegister;

import javax.annotation.Resource;

public class ClassDtoPrepareFactory extends DtoPrepareFactory {

    @Resource
    private ClassDtoModelRegister dtoModelRegister;

    @Resource
    private DtoClassViewRegister dtoClassViewRegister;

    @Override
    public DtoModelRegister getDtoModelRegister() {
        return dtoModelRegister;
    }

    @Override
    public DtoViewRegister getDtoViewRegister() {
        return dtoClassViewRegister;
    }

}
