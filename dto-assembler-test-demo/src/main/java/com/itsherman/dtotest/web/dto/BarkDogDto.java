package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtotest.domain.Dog;

@DtoModel(from = Dog.class)
public interface BarkDogDto extends DogDto {

    Integer getAge();
}
