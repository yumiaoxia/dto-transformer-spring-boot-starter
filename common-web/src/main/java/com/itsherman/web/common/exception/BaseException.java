package com.itsherman.web.common.exception;

import com.itsherman.web.common.enums.IResponseEnum;

import java.io.Serializable;

public class BaseException extends RuntimeException implements Serializable {

    private IResponseEnum responseEnum;
    private Object[] args;

    public BaseException() {
    }

    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;

    }

    public BaseException(IResponseEnum responseEnum, Object[] args) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public BaseException(Throwable cause, IResponseEnum responseEnum, Object[] args) {
        super(responseEnum.getMessage(), cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
