package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtotest.domain.Person;
import com.itsherman.dtotest.domain.Person2;

@DtoModel(from = {Person.class, Person2.class})
public interface PersonMoreDto {

    @DtoProperty
    Long getId();

    @DtoProperty
    String getName();

    @DtoProperty
    Integer getHeight();


}
