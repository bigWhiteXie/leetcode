package com.codexie.代理模式.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodInvocationImpl implements InvocationHandler {
    private Object target;

    public MethodInvocationImpl(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开启事务");

        Object res = method.invoke(target, args);

        System.out.println("提交事务");
        return res;
    }
}
