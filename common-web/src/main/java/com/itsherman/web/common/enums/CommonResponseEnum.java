package com.itsherman.web.common.enums;

public enum CommonResponseEnum implements IResponseEnum {

    SYSTEM_ERROR("9999", "system error"),
    ARGUMENT_NOT_VALID("0001", "argument not valid"),
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
