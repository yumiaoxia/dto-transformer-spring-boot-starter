package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtoassembler.annotations.ViewParser;
import com.itsherman.dtotest.domain.Person;

import java.time.LocalDateTime;

@DtoModel(from = Person.class)
public interface PersonDto {

    @ViewParser(parserClasses = {BasePerson.class, MiniPerson.class, BirthPerson.class})
    @DtoProperty
    Long getId();

    @ViewParser(parserClasses = {BasePerson.class, MiniPerson.class, BirthPerson.class})
    @DtoProperty
    String getName();

    @ViewParser(parserClasses = {MiniPerson.class})
    @DtoProperty
    BookDto getBook();

    CarDto[] getCars();

    @ViewParser(parserClasses = {BirthPerson.class})
    LocalDateTime getBirthday();


    @ViewParser(parserClasses = {BirthPerson.class})
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
