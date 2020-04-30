package com.itsherman.web.common.exception;

import com.itsherman.web.common.enums.IResponseEnum;

public interface ServiceExceptionAssert extends ServiceAssert, IResponseEnum {


    @Override
    default BaseException newException() {
        return new ServiceException(this.getCode());
    }

    @Override
    default BaseException newException(Object... args) {
        return new ServiceException(this.getCode(), args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        return new ServiceException(t, args);
    }
}
