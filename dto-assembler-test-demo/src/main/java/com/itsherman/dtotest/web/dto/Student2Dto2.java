package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtotest.domain.Student2;

import java.util.List;

@DtoModel(from = Student2.class)
public interface Student2Dto2 {

    Long getId();

    String getName();

    @DtoView(viewClass = Void.class)
    List<CourseDo> getCourses();
}
