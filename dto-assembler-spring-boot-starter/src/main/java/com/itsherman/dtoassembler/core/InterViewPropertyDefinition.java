package com.itsherman.dtoassembler.core;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class InterViewPropertyDefinition extends ViewPropertyDefinition {

    private Set<Method> dtoMethods = new HashSet<>();

    public Set<Method> getDtoMethods() {
        return dtoMethods;
    }

    public void setDtoMethods(Set<Method> dtoMethods) {
        this.dtoMethods = dtoMethods;
    }
}
