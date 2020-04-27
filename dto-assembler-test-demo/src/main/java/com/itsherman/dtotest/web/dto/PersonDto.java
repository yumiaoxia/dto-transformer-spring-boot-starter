package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtotest.domain.Person;

import java.time.LocalDateTime;

@DtoModel(from = Person.class)
public interface PersonDto {

    @DtoView(viewClasses = {BasePerson.class, MiniPerson.class, BirthPerson.class})
    @DtoProperty
    Long getId();

    @DtoView(viewClasses = {BasePerson.class, MiniPerson.class, BirthPerson.class})
    @DtoProperty
    String getName();

    @DtoView(viewClasses = {MiniPerson.class})
    @DtoProperty
    BookDto getBook();

    CarDto[] getCars();

    @DtoView(viewClasses = {BirthPerson.class})
    LocalDateTime getBirthday();


    @DtoView(viewClasses = {BirthPerson.class})
    default Integer getAge() {
        LocalDateTime bornDate = getBirthday();
        if (bornDate != null) {
            int year = LocalDateTime.now().getYear();
            return year - bornDate.getYear();
        }
        return -1;
    }

    interface BasePerson {
    }

    interface MiniPerson {
    }

    interface BirthPerson {
    }
}
