package com.itsherman.web.common.exception;

import com.itsherman.web.common.enums.IResponseEnum;

public class ServiceException extends BaseException {

    private String code;

    public ServiceException(IResponseEnum responseEnum) {
        super(responseEnum);
        this.code = responseEnum.getCode();
    }

    public ServiceException(IResponseEnum responseEnum, Object[] args) {
        super(responseEnum, args);
        this.code = responseEnum.getCode();
    }

    public ServiceException(IResponseEnum responseEnum, Object[] args, Throwable cause) {
        super(cause, responseEnum, args);
        this.code = responseEnum.getCode();
    }

    public String getCode() {
        return code;
    }


}
