package com.itsherman.dtoassembler.core;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class DtoInterPropertyDefinition extends ModelPropertyDefinition {

    private Method dtoMethod;

    private Class<?> sourceClass;

    private Method sourceReadMethod;

    private Class<?> viewClass;

    private Set<Class<?>> parserClass = new HashSet<>();

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

    public Class<?> getViewClass() {
        return viewClass;
    }

    public void setViewClass(Class<?> viewClass) {
        this.viewClass = viewClass;
    }

    public Set<Class<?>> getParserClass() {
        return parserClass;
    }

    public void setParserClass(Set<Class<?>> parserClass) {
        this.parserClass = parserClass;
    }
}
