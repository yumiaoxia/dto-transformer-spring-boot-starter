package com.itsherman.dtotest.web.controller;

import com.itsherman.web.common.exception.ServiceExceptionAssert;
import com.itsherman.web.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "框架异常处理测试")
@RestController
@RequestMapping("/api/exception/test")
public class ExceptionTestController {

    @ApiOperation("测试业务异常")
    @GetMapping("/service")
    public ApiResponse<Void> ServiceErr() {
        ServiceExceptionAssert
        return ApiResponse.createSuccess();
    }

    @ApiOperation("测试系统异常")
    @GetMapping("/common")
    public ApiResponse<Void> commonErr(Boolean flag) {
        if (flag) {
            throw new NullPointerException();
        }
        return ApiResponse.createSuccess();
    }
}
