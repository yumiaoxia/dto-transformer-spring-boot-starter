package com.itsherman.dtoassembler.core;

import java.util.ArrayList;
import java.util.List;

public class DtoModelDefinition {

    private int id;

    private Class<?> dtoClass;

    private List<Class<?>> fromClasses;

    private List<ModelPropertyDefinition> mpds = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class<?> getDtoClass() {
        return dtoClass;
    }

    public void setDtoClass(Class<?> dtoClass) {
        this.dtoClass = dtoClass;
    }

    public List<Class<?>> getFromClasses() {
        return fromClasses;
    }

    public void setFromClasses(List<Class<?>> fromClasses) {
        this.fromClasses = fromClasses;
    }

    public List<ModelPropertyDefinition> getMpds() {
        return mpds;
    }
}
