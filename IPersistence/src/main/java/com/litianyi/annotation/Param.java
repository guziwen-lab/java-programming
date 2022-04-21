package com.litianyi.annotation;

import java.lang.annotation.*;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/9 4:24 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Param {
    String value();
}
