package com.itsherman.web.common.exception;

import java.util.function.Supplier;

public interface ServiceAssert extends Assert {

    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException();
        }
    }

    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw newException(args);
        }
    }

    default void assertTrue(boolean flag) {
        if (!flag) {
            throw newException();
        }
    }

    default void assertTrue(boolean flag, Object... args) {
        if (!flag) {
            throw newException(args);
        }
    }

    default void assertTrue(Supplier<Boolean> supplier) {
        if (!supplier.get()) {
            throw newException();
        }
    }

    default void assertTrue(Supplier<Boolean> supplier, Object... args) {
        if (!supplier.get()) {
            throw newException(args);
        }
    }

}
