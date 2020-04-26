package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtotest.domain.Car;

@DtoModel(from = Car.class)
public interface CarDto {

    @DtoProperty(value = "carName")
    String getName();


}
