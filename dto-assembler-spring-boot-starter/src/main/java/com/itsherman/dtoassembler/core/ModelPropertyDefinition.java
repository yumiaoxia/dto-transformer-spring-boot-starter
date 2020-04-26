package com.itsherman.dtoassembler.core;

public class ModelPropertyDefinition {

    private Class<?> sourceClass;

    private String value;

    private DtoModelDefinition md;

    public DtoModelDefinition getMd() {
        return md;
    }

    public void setMd(DtoModelDefinition md) {
        this.md = md;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class<?> getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(Class<?> sourceClass) {
        this.sourceClass = sourceClass;
    }


}
