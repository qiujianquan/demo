package com.example.demo.cglib.service.impl;

/**
 * 测试返回指定参数
 */
public class Service3 {
    public String m1() {
        System.out.println("我是m1方法");
        return "hello:m1";
    }

    public String m2() {
        System.out.println("我是m2方法");
        return "hello:m2";
    }
}
