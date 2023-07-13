package com.codexie.代理模式.jdkProxy;

import com.codexie.代理模式.UserDao;
import com.codexie.代理模式.UserDaoImpl;

import java.lang.reflect.Proxy;

public class TestJdk {
    public static void main(String[] args) {
        //创建被代理对象
        UserDaoImpl userDao = new UserDaoImpl();

        //为被代理对象指定代理业务实现类
        MethodInvocationImpl methodInvocation = new MethodInvocationImpl(userDao);

        //Proxy动态创建代理对象
        UserDao instance = (UserDao) Proxy.newProxyInstance(TestJdk.class.getClassLoader(), userDao.getClass().getInterfaces(), methodInvocation);

        instance.save();

    }
}
