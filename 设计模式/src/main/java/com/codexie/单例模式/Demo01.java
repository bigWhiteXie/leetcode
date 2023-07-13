package com.codexie.单例模式;

/**
 * 饿汉式
 */
public class Demo01 {
    static Demo01 instance = new Demo01();
    private Demo01(){}
    public static Demo01 getInstance(){
        return instance;
    }
    public static void main(String[] args) {

    }
}
