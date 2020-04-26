package com.itsherman.dtoassembler.annotations;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DtoView {

    Class<?> viewClass() default Void.class;
}
