package com.itsherman.dtoassembler.core;

import java.lang.reflect.Method;

public class InterViewPropertyDefinition extends ViewPropertyDefinition {

    private Method dtoMethod;

    public Method getDtoMethod() {
        return dtoMethod;
    }

    public void setDtoMethod(Method dtoMethod) {
        this.dtoMethod = dtoMethod;
    }
}
