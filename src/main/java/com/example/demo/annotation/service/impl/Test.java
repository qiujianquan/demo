package com.example.demo.annotation.service.impl;

import com.example.demo.annotation.service.*;

import java.lang.annotation.ElementType;


@Mess
@Mess1("单参")
@Mess2({"1","2"})
@Mess3(age = 9,address = "北京")
@Mess4(value = "我再类上",elementType = ElementType.TYPE)


public class Test {

    @Mess4(value = "我用在字段上", elementType = ElementType.FIELD)
    private String a;

    @Mess4(value = "我用在构造方法上", elementType = ElementType.CONSTRUCTOR)
    public Test( @Mess4(value = "我用在方法参数上", elementType = ElementType.PARAMETER) String a) {
        this.a = a;
    }

    @Mess4(value = "我用在了普通方法上面", elementType = ElementType.METHOD)
    public void m1() {
        @Mess4(value = "我用在了本地变量上", elementType = ElementType.LOCAL_VARIABLE) String a;
    }

    /**
     * @Mess4上面演示了自定义注解在在类、字段、构造器、方法参数、方法、本地变量上的使用，@Mess4注解有个elementType参数，
     * 我想通过这个参数的值来告诉大家对应@Target中的那个值来限制使用目标的，大家注意一下上面每个elementType的值。
     */




}
