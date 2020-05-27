package com.itsherman.web.common.exception;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

public interface RuntimeExceptionAssert<T extends RuntimeException> extends ExceptionAssert<T> {

    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException();
        }
    }

    default void assertNotNull(Object obj, String... args) {
        if (obj == null) {
            throw newException(args);
        }
    }

    default void assertNotEmpty(String s) {
        if (StringUtils.isEmpty(s)) {
            throw newException();
        }
    }

    default void assertNotEmpty(String s, String... args) {
        if (StringUtils.isEmpty(s)) {
            throw newException(args);
        }
    }

    default void assertNotBlank(String s) {
        if (s == null || s.trim().length() == 0) {
            throw newException();
        }
    }

    default void assertNotBlank(String s, String... args) {
        if (s == null || s.trim().length() == 0) {
            throw newException(args);
        }
    }

    default void assertNotEmpty(Collection collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw newException();
        }
    }

    default void assertNotEmpty(Collection collection, String... args) {
        if (CollectionUtils.isEmpty(collection)) {
            throw newException(args);
        }
    }

    default void assertNotEmpty(Map map) {
        if (CollectionUtils.isEmpty(map)) {
            throw newException();
        }
    }

    default void assertNotEmpty(Map map, String... args) {
        if (CollectionUtils.isEmpty(map)) {
            throw newException(args);
        }
    }

    default void assertTrue(boolean flag) {
        if (flag) {
            throw newException();
        }
    }

    default void assertTrue(boolean flag, String... args) {
        if (flag) {
            throw newException(args);
        }
    }

    default void assertTrue(boolean flag, Throwable cause, String... args) {
        if (flag) {
            throw newException(cause, args);
        }
    }

}

