package com.example.demo.annotation.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 通过default为参数指定默认值，用的时候如果没有设置值，则取默认值，没有指定默认值的参数，使用的时候必须为参数设置值
 *@1：数组类型通过{}指定默认值
 * @2：数组类型参数，默认值只有一个省略了{}符号
 * @3：默认值为30
 * @4：未指定默认值
 * @5：age=32对默认值进行了覆盖，并且为address指定了值
 *
 */
@Target(value = {
        ElementType.TYPE,
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.CONSTRUCTOR,
        ElementType.LOCAL_VARIABLE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mess4 {
    String value();

    ElementType elementType();
}
