package com.itsherman.web.common.enums;

import com.itsherman.web.common.exception.CommonAssert;

public enum CommonResponseEnum implements CommonAssert {

    SYSTEM_ERROR("9999", "system error"),
    OK("0", "success");

    private final String code;
    private final String message;

    CommonResponseEnum(String code, String message) {
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
