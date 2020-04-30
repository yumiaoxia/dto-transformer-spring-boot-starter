package com.itsherman.dtotest.exception;

import com.itsherman.web.common.enums.IResponseEnum;

public enum ResponseEnum implements IResponseEnum {
    SYSTEM_ERROR("9999", "System Error");

    private final String code;


    private final String message;

    ResponseEnum(String code, String message) {
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
