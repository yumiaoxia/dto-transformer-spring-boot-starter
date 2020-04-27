package com.itsherman.dtotest.web.controller;

import com.itsherman.dtoassembler.annotations.ViewSelector;
import com.itsherman.dtoassembler.utils.DtoTransFormer;
import com.itsherman.dtotest.domain.*;
import com.itsherman.dtotest.web.dto.PersonDto;
import com.itsherman.dtotest.web.dto.PersonMoreDto;
import com.itsherman.web.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Api(tags = "Dto 功能性测试")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @ApiOperation("属性含有数组")
    @GetMapping("/detail1/{id}")
    public ApiResponse<PersonDto> getPerson1(@PathVariable("id") Long id) {
        Person person = new Person();
        person.setId(id);
        person.setName("王忠");
        person.setGender(Gender.MALE);

        Book book = new Book();
        book.setBookName("Java 实战");
        person.setBook(book);


        Car car1 = new Car();
        car1.setCarName("路虎");

        Car car2 = new Car();
        car2.setCarName("宝马");

        Car[] cars = new Car[]{car1, car2};
        person.setCars(cars);
        PersonDto result = DtoTransFormer.to(PersonDto.class).apply(person);
        return ApiResponse.createSuccess(result);
    }

    @ApiOperation("多个源对象拼装")
    @GetMapping("/detail2/{id}")
    public ApiResponse<PersonMoreDto> getPerson2(@PathVariable("id") Long id) {
        Person person = new Person();
        person.setId(id);
        person.setName("东丽");


        Person2 person2 = new Person2();
        person2.setHeight(178);

        PersonMoreDto result = DtoTransFormer.to(PersonMoreDto.class).apply(person, person2);
        return ApiResponse.createSuccess(result);
    }

    @ViewSelector(selectView = PersonDto.BasePerson.class)
    @ApiOperation("带@DtoView注解,viewClass=BasePerson")
    @GetMapping("/detail3/{id}")
    public ApiResponse<PersonDto> getPerson3(@PathVariable("id") Long id) {
        Person person = new Person();
        person.setId(id);
        person.setName("王忠");
        person.setGender(Gender.MALE);

        Book book = new Book();
        book.setBookName("Java 实战");
        person.setBook(book);

        PersonDto result = DtoTransFormer.to(PersonDto.class).apply(person);
        return ApiResponse.createSuccess(result);
    }

    @ViewSelector(selectView = PersonDto.MiniPerson.class)
    @ApiOperation("带@DtoView注解,viewClass=MiniPerson")
    @GetMapping("/detail4/{id}")
    public ApiResponse<PersonDto> getPerson4(@PathVariable("id") Long id) {
        Person person = new Person();
        person.setId(id);
        person.setName("王忠");
        person.setGender(Gender.MALE);

        Book book = new Book();
        book.setBookName("Java 实战");
        person.setBook(book);

        PersonDto result = DtoTransFormer.to(PersonDto.class).apply(person);
        return ApiResponse.createSuccess(result);
    }

    @ViewSelector(selectView = PersonDto.BirthPerson.class)
    @ApiOperation("带@DtoView注解,viewClass=BirthPerson")
    @GetMapping("/detail5/{id}")
    public ApiResponse<PersonDto> getPerson5(@PathVariable("id") Long id) {
        Person person = new Person();
        person.setId(id);
        person.setName("憨批");
        person.setBirthday(LocalDateTime.now().minusYears(25));

        PersonDto result = DtoTransFormer.to(PersonDto.class).apply(person);
        return ApiResponse.createSuccess(result);
    }


}
