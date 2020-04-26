package com.itsherman.dtoassembler.task;

public class InterfaceDtoAssembler<T, R> implements DtoAssembler<T, R> {


    @SafeVarargs
    @Override
    public final R assemble(Class<R> dtoClass, Class<?> viewClass, T... ts) {
        return (R) new DtoInterfaceProxy<T, R>().getInstance(dtoClass, viewClass, ts);
    }

}
