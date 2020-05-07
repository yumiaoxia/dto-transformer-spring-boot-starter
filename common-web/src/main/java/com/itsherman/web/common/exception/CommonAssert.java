package com.itsherman.web.common.exception;

import com.itsherman.web.common.enums.IResponseEnum;

public interface CommonAssert extends Assert, IResponseEnum {
    @Override
    default BaseException newException() {
        return new BaseException();
    }

    @Override
    default BaseException newException(Object... args) {
        return new BaseException(this, args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        return new BaseException(t, this, args);
    }
}
