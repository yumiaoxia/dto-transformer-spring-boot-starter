package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtotest.entity.Teacher;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@DtoModel(from = Teacher.class)
@ApiModel
public interface TeacherDto extends Serializable {

    @ApiModelProperty
    String getName();
}
