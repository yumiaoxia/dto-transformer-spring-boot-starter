package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtotest.domain.Course;

@DtoModel(from = Course.class)
public interface CourseDto {

    @DtoView(viewClasses = BaseCourse.class)
    @DtoProperty(value = "courseName")
    String getName();


    Double getScore();

    interface BaseCourse {
    }
}
