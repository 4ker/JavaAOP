package com.tangzhixiong.javaaop.annon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Seven {

    // for fields
    String value() default "🐶";

    // for methods
    String Property() default "无属性";

}
