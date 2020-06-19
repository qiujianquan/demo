package com.example.demo.annotation.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 value 的类型是一个String类型的数组
 value 有多个值的时候，需要使用{}包含起来
 如果value 只有一个值，{}可以省略
 *
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mess2 {
    String[] value();
}
