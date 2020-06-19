package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 指定当前注解解可以用在类、接口、注解类型、枚举类型以及方法上面，自定义注解上也可以不使用@Target注解，如果不使用，表示自定义注解可以用在任何地方。
 */
@Target(value = {ElementType.TYPE,ElementType.METHOD})
public @interface MyAnnotation {
}
