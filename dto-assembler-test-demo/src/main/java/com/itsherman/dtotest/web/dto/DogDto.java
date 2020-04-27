package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtotest.domain.Dog;

@DtoModel(from = Dog.class)
public interface DogDto {

    @DtoView(viewClasses = {DogDo.MiniDog.class, DogDo.BaseDog.class})
    String getFirstName();

    @DtoView(viewClasses = {DogDo.MiniDog.class, DogDo.BaseDog.class})
    String getLastName();

    @DtoView(viewClasses = DogDo.BaseDog.class, referenceNames = {"getFirstName", "getLastName"})
    default String getFullName() {
        if (getFirstName() != null && getLastName() != null) {
            return getFirstName() + " " + getLastName();
        }
        return null;
    }
}
