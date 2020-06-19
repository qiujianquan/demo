package com.example.demo.annotation.service.impl;

import com.example.demo.annotation.service.Mess6;

import java.util.Map;

/**
 * 类后面的V1、V2都是类型名称，Map后面的尖括号也是类型名称，m1方法前面也定义了一个类型变量，名称为T
 * @param <V1>
 * @param <V2>
 */
@Mess6("用在了类上")
public class UserAnnotation10<@Mess6("用在了类变量类型V1上") V1, @Mess6("用在了类变量类型V2上") V2> {

    private Map<@Mess6("用在了泛型类型上") String, Integer> map;

    public <@Mess6("用在了参数上") T> String m1(String name) {
        return null;
    }

}
