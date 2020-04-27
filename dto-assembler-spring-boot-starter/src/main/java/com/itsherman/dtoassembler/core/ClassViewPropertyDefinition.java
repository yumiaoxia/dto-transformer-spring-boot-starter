package com.itsherman.dtoassembler.core;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ClassViewPropertyDefinition extends ViewPropertyDefinition {

    private Set<Field> fields = new HashSet<>();

    public Set<Field> getFields() {
        return fields;
    }
}
