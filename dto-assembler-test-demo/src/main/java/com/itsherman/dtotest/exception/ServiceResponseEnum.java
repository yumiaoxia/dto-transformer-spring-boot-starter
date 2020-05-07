package com.itsherman.dtotest.exception;

import com.itsherman.web.common.exception.ServiceExceptionAssert;

public enum ServiceResponseEnum implements ServiceExceptionAssert {
    SYSTEM_ERROR("9999", "System Error"),
    PARAMETER_ERROR("1001", "parameter error"),
    CAUSE_ERROR("2001", "cause error");

    private final String code;


    private final String message;

    ServiceResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
