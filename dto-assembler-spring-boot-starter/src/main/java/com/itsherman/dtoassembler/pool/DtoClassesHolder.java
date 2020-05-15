package com.itsherman.dtoassembler.pool;

import java.util.HashSet;
import java.util.Set;

public class DtoClassesHolder {

    private static final DtoClassesHolder DTO_CLASSES_HOLDER = new DtoClassesHolder();
    private Set<Class<?>> dtoClasses = new HashSet<>();

    private DtoClassesHolder() {
    }

    public static DtoClassesHolder getInstance() {
        return DTO_CLASSES_HOLDER;
    }

    public Set<Class<?>> getDtoClasses() {
        return dtoClasses;
    }
}
