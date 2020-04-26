package com.itsherman.dtotest.web.command;

import com.itsherman.dtotest.domain.Gender;

import java.io.Serializable;
import java.util.Set;

public class StudentCreateCommand implements Serializable {

    private String name;

    private Gender gender;

    private Set<com.itsherman.dtotest.web.command.TeacherCreateCommand> teachers;

    public Set<com.itsherman.dtotest.web.command.TeacherCreateCommand> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<com.itsherman.dtotest.web.command.TeacherCreateCommand> teachers) {
        this.teachers = teachers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
