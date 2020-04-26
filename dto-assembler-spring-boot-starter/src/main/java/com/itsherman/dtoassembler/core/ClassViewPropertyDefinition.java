package com.itsherman.dtoassembler.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassViewPropertyDefinition extends ViewPropertyDefinition {

    private List<Field> fields = new ArrayList<>();

    public List<Field> getFields() {
        return fields;
    }

}
