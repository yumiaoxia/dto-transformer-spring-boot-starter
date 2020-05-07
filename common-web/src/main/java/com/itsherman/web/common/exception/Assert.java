package com.itsherman.web.common.exception;

public interface Assert {

    BaseException newException();

    BaseException newException(Object... args);

    BaseException newException(Throwable t, Object... args);

    default Throwable cause(Throwable t, Object... args) {
        throw newException(t, args);
    }
}
