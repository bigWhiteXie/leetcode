package com.codexie.单例模式;

/**
 * 静态内部类
 */
public class Demo04 {
    private static class Inner{
        private static Demo04 instance = new Demo04();
    }


    private Demo04(){

    }

    public static Demo04 getInstance(){
      return Inner.instance;
    }
}
