package com.itsherman.dtotest.web.command;

import java.io.Serializable;

public class TeacherCreateCommand implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
