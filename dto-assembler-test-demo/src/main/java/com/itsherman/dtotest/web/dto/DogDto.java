package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.ViewParser;
import com.itsherman.dtotest.domain.Dog;

@DtoModel(from = Dog.class)
public interface DogDto {

    @ViewParser(parserClasses = {DogDo.MiniDog.class, DogDo.BaseDog.class})
    String getFirstName();

    @ViewParser(parserClasses = {DogDo.MiniDog.class, DogDo.BaseDog.class})
    String getLastName();

    @ViewParser(parserClasses = DogDo.BaseDog.class, referenceFieldNames = {"getFirstName", "getLastName"})
    default String getFullName() {
        if (getFirstName() != null && getLastName() != null) {
            return getFirstName() + " " + getLastName();
        }
        return null;
    }
}
