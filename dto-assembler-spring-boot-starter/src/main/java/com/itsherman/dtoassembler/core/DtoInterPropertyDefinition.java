package com.itsherman.dtoassembler.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DtoInterPropertyDefinition extends ModelPropertyDefinition {

    private Method dtoMethod;

    private Class<?> sourceClass;

    private Method sourceReadMethod;

    private Class<?> viewCLass;

    private List<Class<?>> parserClass = new ArrayList<>();

    public Method getDtoMethod() {
        return dtoMethod;
    }

    public void setDtoMethod(Method dtoMethod) {
        this.dtoMethod = dtoMethod;
    }

    @Override
    public Class<?> getSourceClass() {
        return sourceClass;
    }

    @Override
    public void setSourceClass(Class<?> sourceClass) {
        this.sourceClass = sourceClass;
    }

    public Method getSourceReadMethod() {
        return sourceReadMethod;
    }

    public void setSourceReadMethod(Method sourceReadMethod) {
        this.sourceReadMethod = sourceReadMethod;
    }

    public Class<?> getViewCLass() {
        return viewCLass;
    }

    public void setViewCLass(Class<?> viewCLass) {
        this.viewCLass = viewCLass;
    }

    public List<Class<?>> getParserClass() {
        return parserClass;
    }

    public void setParserClass(List<Class<?>> parserClass) {
        this.parserClass = parserClass;
    }
}
