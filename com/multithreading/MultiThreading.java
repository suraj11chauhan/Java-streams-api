package com.test.practices.multithreading;

/*
* In java, Multithreading is the concurrent execution of two or more threads to maximize the utilization of the CPU.
* Java's multithreading capabilities are the part of java.lang package, making easy to implement concurrent execution
*
* In a single-core environment, Java's multithreading is managed by the JVM and the OS, which switch between threads to
* give the illusion of concurrency
*
* The threads share the singe core, and time slicing is used to managed thread execution.
*
* In a multi-core environment, Java's multithreading can take full advantage of the available cores.
*
* The JVM can distribute threads across multiple cores allowing true parallel execution of threads.
*
* A thread is a lightweight process, the smallest unit of processing. Java support multithreading through its
* java.lang.Thread class and java.lang.Runnable Interface.

* When a java program starts. one thread begins running immediately, which is called the main thread. This thread is
* responsible for executing the main method of a program.
*
* --------------------------------------------
* 1. Start a thread by extending Thread class
* a)A new class ThreadTest is created that extends Thread class.
* b)The run() method is overridden to define the code that constitutes the new thread.
* c)start() method is called to initiate the new thread.
*
* 2. Start a thread by Implement Runnable interface
* a)A new class RunnableTest is created that Implement Runnable interface.
* b)The run() method is overridden to define the code that constitutes the new thread.
* c)A Thread object is created by passing an instance of RunnableTest.
* d)start() method is called on the Thread object to initiate the new thread.
*
* In both cases, run() method contains the codes that will be executed in the new thread.
* --------------------------------------------
* Thread Life Cycle
* 1. New: A thread is in this state when it is created but not yet started.
* 2. Runnable: After the start method is called, the thread becomes runnable,It's ready to run and is waiting for CPU time.
* 3. Running: The thread is in this state when it is executing.
* 4. Block/Wait: A thread is in this state when it is waiting for a resource or for another thread to perform an action.
* 5. Terminate:  A thread is in this state when it has finished executing.
*
* join() is a synchronization method that blocks the calling thread (that is, the thread that calls the method)
* until the thread whose Join method is called has completed.
* */
public class MultiThreading {
    public static void main(String[] args) throws InterruptedException {

        // By extending Thread class
        /*ThreadTest world_ThreadTest = new ThreadTest();
        world_ThreadTest.start();
        for (int i = 0; i < 100000; i++) {
            System.out.println("Hello-ThreadTest");
        }*/

        // By implementing Runnable Interface
       /* RunnableTest world_RunnableTest = new RunnableTest();
        Thread thread_RunnableTest = new Thread(world_RunnableTest);
        thread_RunnableTest.start();
        for (int i = 0; i < 100000; i++) {
            System.out.println("Hello-RunnableTest");
        }*/

        //Using lambda expression
        /*Runnable r = () -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("Hello-Thread");
                System.out.println(Thread.currentThread().getState().name());
            }
        };
        Thread t = new Thread(r);
        t.start();*/

        //Thread lifecycle
        /*ThreadTest world_Thread = new ThreadTest();// NEW state
        System.out.println(world_Thread.getState().name());
        world_Thread.start(); // RUNNABLE state
        System.out.println(world_Thread.getState().name());
        //Thread.sleep(1000); // sleep the current thread => TIMED_WAITING state
        world_Thread.join(); // join() using this method current thread wait till finish the  execution of world_Thread thread
        System.out.println("current thread: "+Thread.currentThread().getState().name());
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello-Thread-"+i);
            System.out.println(world_Thread.getState().name());
        }*/

        //methods:  start run sleep join setPriority interrupt yield
        /*Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    //Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"-"+i);
                    //Thread.yield();// provide hits to cpu to run other thread with same priority
                    if(i == 500)
                     Thread.currentThread().join();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(r,"AAAAA");
        Thread t2 = new Thread(r,"BBBBB");
        Thread t3 = new Thread(r,"CCCCC");
        t1.start();
        //t1.setPriority(Thread.MAX_PRIORITY);
        t2.start();
        //t2.setPriority(Thread.MIN_PRIORITY);
        t3.start();
        //Thread.sleep(1);
        //t1.join();
        t1.interrupt();
        //t2.interrupt();*/

        //Examples of daemon Thread

        Thread userThread = new Thread(() -> {
            int i=0;
            while (i<100000) {
                System.out.println(Thread.currentThread().getName()+"-"+i);
                i++;
            }
        },"user-thread");

        Thread daemonThread = new Thread(() -> {
            int i=0;
            while (true) {
                System.out.println(Thread.currentThread().getName()+"-"+i);
                i++;
            }
        },"daemon-thread");
        daemonThread.setDaemon(true);

        //start both threads
        userThread.start();
        daemonThread.start();

        System.out.println("Main done!!");




    }
}
