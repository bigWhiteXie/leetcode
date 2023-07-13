package com.codexie.代理模式;

public class UserDaoImpl implements UserDao{
    @Override
    public void save() {
        System.out.println("插入对象");
    }
}
