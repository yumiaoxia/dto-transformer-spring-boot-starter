package com.itsherman.dtotest.web.controller;

import com.itsherman.dtotest.exception.ServiceResponseEnum;
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
        Object obj = null;
        ServiceResponseEnum.SYSTEM_ERROR.assertNotNull(obj);
        return ApiResponse.createSuccess();
    }

    @ApiOperation("测试携带参数业务异常")
    @GetMapping("/service/parameter")
    public ApiResponse<Void> ServicePatamErr() {
        Object obj = null;
        ServiceResponseEnum.PARAMETER_ERROR.assertNotNull(obj, "parameter");
        return ApiResponse.createSuccess();
    }

    @ApiOperation("测试运行实异常导致业务异常")
    @GetMapping("/service/newException")
    public ApiResponse<Void> ServicenewErr() {
        ServiceResponseEnum.CAUSE_ERROR.cause(new NullPointerException(), "sql");
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
