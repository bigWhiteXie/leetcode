package com.codexie.单例模式;

import java.util.HashMap;

/**
 * 双重检测锁模式
 */
public class Demo03 {
    private static Demo03 instance;


    private Demo03(){
        String s = "12";
        HashMap<Character, Integer> map = new HashMap<>();
        System.out.println(map.putIfAbsent('a', 1));
        System.out.println(map.putIfAbsent('a', 1));
    }


    public static Demo03 getInstance(){
        if(instance == null){
            synchronized (Demo03.class){
                if(instance == null){
                    instance = new Demo03();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Demo03.getInstance());
    }
}
