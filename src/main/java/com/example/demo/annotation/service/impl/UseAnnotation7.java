package com.example.demo.annotation.service.impl;

import com.example.demo.annotation.service.Mess5;

import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class UseAnnotation7<@Mess5("T0是在类上声明的一个泛型类型变量") T0, @Mess5("T1是在类上声明的一个泛型类型变量") T1> {

    public <@Mess5("T2是在方法上声明的泛型类型变量") T2> void m1() {
    }

    public static void main(String[] args) throws NoSuchMethodException {
        for (TypeVariable typeVariable : UseAnnotation7.class.getTypeParameters()) {
            print(typeVariable);
        }

        for (TypeVariable typeVariable : UseAnnotation7.class.getDeclaredMethod("m1").getTypeParameters()) {
            print(typeVariable);
        }
    }

    private static void print(TypeVariable typeVariable) {
        System.out.println("类型变量名称:" + typeVariable.getName());
        //获取注解内容并输出
        Arrays.stream(typeVariable.getAnnotations()).forEach(System.out::println);
    }
}
