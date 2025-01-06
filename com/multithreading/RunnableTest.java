package com.test.practices.multithreading;

public class RunnableTest implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            System.out.println("World-RunnableTest");
        }
    }
}
