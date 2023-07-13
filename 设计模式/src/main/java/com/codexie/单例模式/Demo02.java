package com.codexie.单例模式;

/**
 * 懒汉式
 */
public class Demo02 {
    private static Demo02 instance;


    private Demo02(){

    }

    public synchronized static  Demo02 getInstance(){
        if(instance == null){
            instance = new Demo02();
        }
        return instance;
    }
}
