package com.itsherman.web.common.exception;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ExceptionAssertHandler<T extends RuntimeException> extends ExceptionAssert<T> {

    default ExceptionHandler<T> assertNotNull(Object obj) {
        return assertTrue(obj != null);
    }

    default ExceptionHandler<T> assertNotNull(Object obj, String... args) {
        return assertTrue(obj != null, args);
    }

    default ExceptionHandler<T> assertNotEmpty(String s) {
        return assertTrue(!StringUtils.isEmpty(s));
    }

    default ExceptionHandler<T> assertNotEmpty(String s, String... args) {
        return assertTrue(!StringUtils.isEmpty(s), args);
    }

    default ExceptionHandler<T> assertNotBlank(String s) {
        return assertTrue(s != null && s.trim().length() != 0);
    }

    default ExceptionHandler<T> assertNotBlank(String s, String... args) {
        return assertTrue(s != null && s.trim().length() != 0, args);
    }

    default ExceptionHandler<T> assertNotEmpty(Collection collection) {
        return assertTrue(!CollectionUtils.isEmpty(collection));
    }

    default ExceptionHandler<T> assertNotEmpty(Collection collection, String... args) {
        return assertTrue(!CollectionUtils.isEmpty(collection), args);
    }

    default ExceptionHandler<T> assertNotEmpty(Map map) {
        return assertTrue(!CollectionUtils.isEmpty(map));
    }

    default ExceptionHandler<T> assertNotEmpty(Map map, String... args) {
        return assertTrue(!CollectionUtils.isEmpty(map), args);
    }

    default ExceptionHandler<T> assertTrue(boolean flag) {
        ExceptionHandler<T> handler = new ExceptionHandler<>();
        if (!flag) {
            handler.setException(newException());
        }
        return handler;
    }

    default ExceptionHandler<T> assertTrue(boolean flag, String... args) {
        ExceptionHandler<T> handler = new ExceptionHandler<>();
        if (!flag) {
            handler.setException(newException(args));
        }
        return handler;
    }

    default ExceptionHandler<T> assertTrue(boolean flag, Throwable cause, String... args) {
        ExceptionHandler<T> handler = new ExceptionHandler<>();
        if (!flag) {
            handler.setException(newException(cause, args));
        }
        return handler;
    }

    class ExceptionHandler<T extends RuntimeException> {

        private T exception;

        public void consume(Consumer<T> consumer) {
            if (exception != null) {
                consumer.accept(exception);
            }
        }

        public <R> Optional<R> apply(Function<T, R> fun) {
            if (exception != null) {
                return Optional.of(fun.apply(exception));
            }
            return Optional.empty();
        }

        public void raise() {
            if (exception != null) {
                throw exception;
            }
        }

        public T getException() {
            return exception;
        }

        public void setException(T exception) {
            this.exception = exception;
        }
    }
}
