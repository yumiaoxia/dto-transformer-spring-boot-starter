package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtotest.domain.Gender;
import com.itsherman.dtotest.entity.Student;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Set;

@DtoModel(from = Student.class)
public interface StudentDto extends Serializable {

    @DtoView(viewClasses = {BaseStudent.class})
    @ApiModelProperty
    Long getId();

    @ApiModelProperty
    Gender getGender();

    @DtoView(viewClasses = {BaseStudent.class})
    @ApiModelProperty
    String getName();

    @ApiModelProperty
    Set<TeacherDto> getTeachers();

    class BaseStudent {
    }
}
