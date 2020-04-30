package com.itsherman.web.common.exception;

import java.io.Serializable;

public class BaseException extends RuntimeException implements Serializable {

    private String code;
    private Object[] args;

    public BaseException(String code) {
        this.code = code;
    }

    public BaseException(String code, Object[] args) {
        this.code = code;
        this.args = args;
    }

    public BaseException(String code, Object[] args, Throwable cause) {
        super(cause);
        this.code = code;
        this.args = args;
    }

    public BaseException() {
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }
}
