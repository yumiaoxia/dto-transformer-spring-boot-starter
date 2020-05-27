package com.itsherman.web.common.exception;

public interface ExceptionAssert<T extends RuntimeException> extends Assert {

    T newException();

    T newException(String... args);

    T newException(Throwable t, String... args);

    default void cause(Throwable t, String... args) throws Exception {
        throw newException(t, args);
    }

}
