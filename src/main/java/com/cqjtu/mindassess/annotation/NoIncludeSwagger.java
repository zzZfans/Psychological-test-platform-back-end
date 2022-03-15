package com.cqjtu.mindassess.annotation;

import java.lang.annotation.*;

/**
 * @author zhangning
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoIncludeSwagger {
    String value() default "";
}
