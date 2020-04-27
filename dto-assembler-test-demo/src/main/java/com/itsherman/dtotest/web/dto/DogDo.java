package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtoassembler.annotations.ViewParser;
import com.itsherman.dtotest.domain.Dog;

@DtoModel(from = Dog.class)
public class DogDo {

    @ViewParser(parserClasses = {MiniDog.class})
    private String firstName;


    @ViewParser(parserClasses = {MiniDog.class})
    private String lastName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ViewParser(parserClasses = {BaseDog.class}, referenceFieldNames = {"firstName", "lastName"})
    public String getFullName() {
        if (getFirstName() != null && getLastName() != null) {
            return getFirstName() + " " + getLastName();
        }
        return null;
    }

    public interface BaseDog {
    }

    public interface MiniDog {
    }


}
