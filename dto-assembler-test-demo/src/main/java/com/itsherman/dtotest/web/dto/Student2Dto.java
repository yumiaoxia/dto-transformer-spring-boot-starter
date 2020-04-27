package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.ViewSelector;
import com.itsherman.dtotest.domain.Student2;

import java.util.List;

@DtoModel(from = Student2.class)
public interface Student2Dto {

    Long getId();

    String getName();

    @ViewSelector(selectView = CourseDto.BaseCourse.class)
    List<CourseDto> getCourses();
}
