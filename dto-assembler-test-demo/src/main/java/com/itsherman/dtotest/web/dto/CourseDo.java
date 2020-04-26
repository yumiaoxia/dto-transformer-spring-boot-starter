package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtoassembler.annotations.ViewParser;
import com.itsherman.dtotest.domain.Course;

@DtoModel(from = Course.class)
public class CourseDo {

    @DtoProperty(value = "courseName")
    @ViewParser(parserClasses = {BaseCourse2.class})
    private String name;

    @ViewParser(parserClasses = {Void.class})
    private Double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    interface BaseCourse2 {
    }
}
