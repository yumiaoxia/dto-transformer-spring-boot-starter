package com.itsherman.dtoassembler.task;

public interface DtoAssembler<T, R> {

    R assemble(Class<R> dtoClass, Class<?> viewClass, T... ts);
}
