package com.example.demo.annotation.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @1：注解之后一个参数，名称为value
 * @2：使用注解，参数名称value省略了
 *
 * 如果参数部位value  需要写上变量名称
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mess1 {
    String value();
}
