package com.itsherman.dtoassembler.utils;


import com.itsherman.dtoassembler.exception.DtoAssembleException;
import com.itsherman.dtoassembler.task.ClassDtoAssembler;
import com.itsherman.dtoassembler.task.DtoAssembler;
import com.itsherman.dtoassembler.task.InterfaceDtoAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DtoAssembleUtils {

    private static final Logger log = LoggerFactory.getLogger(DtoAssembleUtils.class);

    public static <T, R> R assemble(Class<R> dtoClass, Class<?> viewClass, T... ts) {
        if (ts.length > 0) {
            for (T t : ts) {
                if (t == null) {
                    log.error("Can not transform dto,the source object must not be null!");
                    return null;
                }
            }
        }
        DtoAssembler<T, R> dtoAssembler;
        if (dtoClass.isInterface()) {
            dtoAssembler = new InterfaceDtoAssembler<>();
        } else if (dtoClass.getSuperclass() != null) {
            dtoAssembler = new ClassDtoAssembler<>();
        } else {
            throw new DtoAssembleException(String.format("can not just dtoClass '%s',it is not interface and has not supperClass", dtoClass));
        }
        return dtoAssembler.assemble(dtoClass, viewClass, ts);
    }
}
