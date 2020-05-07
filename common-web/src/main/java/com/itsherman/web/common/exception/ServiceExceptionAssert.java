package com.itsherman.web.common.exception;

import com.itsherman.web.common.enums.IResponseEnum;

public interface ServiceExceptionAssert extends ServiceAssert, IResponseEnum {

    @Override
    default ServiceException newException() {
        return new ServiceException(this);
    }

    @Override
    default ServiceException newException(Object... args) {
        return new ServiceException(this, args);
    }

    @Override
    default ServiceException newException(Throwable t, Object... args) {
        return new ServiceException(this, args, t);
    }
}
