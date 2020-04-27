package com.itsherman.dtotest.web.controller;

import com.itsherman.dtoassembler.annotations.DtoView;
import com.itsherman.dtoassembler.utils.DtoTransFormer;
import com.itsherman.dtotest.domain.Dog;
import com.itsherman.dtotest.web.dto.DogDo;
import com.itsherman.dtotest.web.dto.DogDto;
import com.itsherman.web.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "计算属性")
@RestController
@RequestMapping("/api/dog")
public class DogController {

    @DtoView(viewClass = DogDo.BaseDog.class)
    @ApiOperation("计算全名,类")
    @GetMapping("/detail")
    public ApiResponse<DogDo> detail() {
        Dog dog = new Dog();
        dog.setFirstName("Smish");
        dog.setLastName("dog");
        DogDo dogDo = DtoTransFormer.to(DogDo.class).apply(dog);
        return ApiResponse.createSuccess(dogDo);

    }

    @DtoView(viewClass = DogDo.MiniDog.class)
    @ApiOperation("计算全名，接口")
    @GetMapping("/detail2")
    public ApiResponse<DogDto> detail2() {
        Dog dog = new Dog();
        dog.setFirstName("Ali");
        dog.setLastName("dog");
        DogDto result = DtoTransFormer.to(DogDto.class).apply(dog);
        return ApiResponse.createSuccess(result);
    }


}
