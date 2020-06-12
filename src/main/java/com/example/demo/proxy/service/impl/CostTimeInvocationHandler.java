package com.example.demo.proxy.service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 主要是createProxy方法用来创建代理对象，2个参数：
 * target：目标对象，需要实现targetInterface接口
 * targetInterface：需要创建代理的接口
 * invoke方法中通过method.invoke(this.target, args)调用目标方法，然后统计方法的耗时。
 */
public class CostTimeInvocationHandler implements InvocationHandler {
    private  Object target;

    public CostTimeInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *  代理 操作类
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime=System.currentTimeMillis();
        Object result=method.invoke(this.target,args);
        long endTime =System.currentTimeMillis();
        System.out.println(this.target.getClass() + ".m1()方法耗时(纳秒):" + (endTime - startTime));

        return result;

    }

    /**
     * 用来创建targetInterface接口的代理对象
     *
     * @param target          需要被代理的对象
     * @param targetInterface 被代理的接口
     * @param <T>
     * @return
     */
    public static <T> T createProxy(Object target, Class<T> targetInterface) {
        if (!targetInterface.isInterface()) {
            throw new IllegalStateException("targetInterface必须是接口类型!");
        } else if (!targetInterface.isAssignableFrom(target.getClass())) {
            throw new IllegalStateException("target必须是targetInterface接口的实现类!");
        }
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new CostTimeInvocationHandler(target));
    }

}
