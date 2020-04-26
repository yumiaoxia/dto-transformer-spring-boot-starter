package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtotest.entity.Student;

import java.util.Set;

@DtoModel(from = {Student.class})
public class StudentDo {

    private Long id;

    private String name;

    private Set<TeacherDto> teachers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TeacherDto> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherDto> teachers) {
        this.teachers = teachers;
    }
}
