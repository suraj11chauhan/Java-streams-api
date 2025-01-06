package com.test.practices.multithreading;

//1. Start thread by extending Thread class

public class ThreadTest extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("World-ThreadTest-"+i);
            System.out.println(Thread.currentThread().getState().name());
            try {
                Thread.sleep(1); // sleep the current thread => TIMED_WAITING state
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
