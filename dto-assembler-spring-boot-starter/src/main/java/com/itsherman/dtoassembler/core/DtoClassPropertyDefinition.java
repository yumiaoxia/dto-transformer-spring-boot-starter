package com.itsherman.dtoassembler.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class DtoClassPropertyDefinition extends ModelPropertyDefinition {

    private Method dtoWriteMethod;

    private Field dtoField;

    private Method dtoReadMethod;

    private Class<?> sourceClass;

    private Method sourceReadMethod;

    private Class<?> viewClass = Void.class;

    private String expectFieldName;

    private Set<Class<?>> parserClass = new HashSet<>();

    private String[] referenceFieldNames;

    public Method getDtoWriteMethod() {
        return dtoWriteMethod;
    }

    public void setDtoWriteMethod(Method dtoWriteMethod) {
        this.dtoWriteMethod = dtoWriteMethod;
    }

    public Field getDtoField() {
        return dtoField;
    }

    public void setDtoField(Field dtoField) {
        this.dtoField = dtoField;
    }


    public String getExpectFieldName() {
        return expectFieldName;
    }

    public void setExpectFieldName(String expectFieldName) {
        this.expectFieldName = expectFieldName;
    }

    public Method getDtoReadMethod() {
        return dtoReadMethod;
    }

    public void setDtoReadMethod(Method dtoReadMethod) {
        this.dtoReadMethod = dtoReadMethod;
    }

    public Class<?> getSourceClass() {
        return sourceClass;
    }

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

    public String[] getReferenceFieldNames() {
        return referenceFieldNames;
    }

    public void setReferenceFieldNames(String[] referenceFieldNames) {
        this.referenceFieldNames = referenceFieldNames;
    }
}
