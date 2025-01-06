# Multithreading in Java

- **Definition**  
  Multithreading is the concurrent execution of two or more threads to maximize the utilization of the CPU. Java's multithreading capabilities are part of the `java.lang` package, making it easy to implement concurrent execution.

- **Single-Core Environment**
    - In a single-core environment, Java's multithreading is managed by the JVM and the OS.
    - Threads are switched between by the OS and JVM to give the illusion of concurrency.
    - Threads share the single core, and time slicing is used to manage thread execution.

- **Multi-Core Environment**
    - In a multi-core environment, Java's multithreading can fully utilize the available cores.
    - The JVM distributes threads across multiple cores, allowing true parallel execution of threads.

- **Thread Basics**
    - A thread is a lightweight process and the smallest unit of processing.
    - Java supports multithreading through its `java.lang.Thread` class and `java.lang.Runnable` interface.
    - When a Java program starts, one thread (called the main thread) begins running immediately, responsible for executing the `main` method.

---

## Starting a Thread

1. **By Extending the `Thread` Class**
    - A new class, `ThreadTest`, is created that extends the `Thread` class.
    - The `run()` method is overridden to define the code that constitutes the new thread.
    - The `start()` method is called to initiate the new thread.

2. **By Implementing the `Runnable` Interface**
    - A new class, `RunnableTest`, is created that implements the `Runnable` interface.
    - The `run()` method is overridden to define the code that constitutes the new thread.
    - A `Thread` object is created by passing an instance of `RunnableTest`.
    - The `start()` method is called on the `Thread` object to initiate the new thread.

   > In both cases, the `run()` method contains the code that will be executed in the new thread.

---

## Thread Life Cycle

1. **New**
    - A thread is in this state when it is created but not yet started.

2. **Runnable**
    - After the `start()` method is called, the thread becomes runnable.
    - It is ready to run and is waiting for CPU time.

3. **Running**
    - The thread is in this state when it is executing.

4. **Blocked/Waiting**
    - A thread is in this state when it is waiting for a resource or for another thread to perform an action.

5. **Terminated**
    - A thread is in this state when it has finished executing.


## Key Thread Methods

1. **`start()`**
    - Initiates a thread by starting its execution.
    - Internally calls the `run()` method.
    - A thread must be in the **New** state for `start()` to be called.

2. **`run()`**
    - Contains the code to be executed by the thread.
    - Can be called directly but won't initiate a new thread; it runs in the current thread.

3. **`sleep(long millis)`**
    - Pauses the thread for the specified duration in milliseconds.
    - Throws `InterruptedException` if another thread interrupts it during sleep.

4. **`join()`**
    - Waits for a thread to complete its execution.
    - Allows one thread to block until another thread finishes.

5. **`setPriority(int priority)`**
    - Sets the priority of a thread.
    - Valid range is from `Thread.MIN_PRIORITY` (1) to `Thread.MAX_PRIORITY` (10), with the default being `Thread.NORM_PRIORITY` (5).

6. **`interrupt()`**
    - Interrupts a thread in **sleep**, **wait**, or **join** state by throwing an `InterruptedException`.
    - Does not stop a thread but signals it to check its state.

7. **`yield()`**
    - Suggests the current thread should pause and allow other threads of the same priority to execute.
    - Does not guarantee relinquishing control; it depends on the thread scheduler `Thread.yield()`.

8. **`setDaemon()`**
    - You can make a thread a daemon thread by calling `setDaemon(true)` before starting the thread.

> join() is a synchronization method that blocks the calling thread (that is, the thread that calls the method)
until the thread whose Join method is called has completed. OR Thread class provides the join() method which allows one thread to wait until another thread completes its execution

Here’s an explanation of **User Threads** and **Daemon Threads** in Java:

---

### **User Thread**
- A **User Thread** is a thread that performs the primary operations of a Java application.
- It is created by default when a program starts, and the main thread is an example of a user thread.
- The JVM will not terminate until all user threads have completed execution.
- These threads are essential for application functionality and are not terminated unless explicitly stopped or their task finishes.

**Key Characteristics:**
1. Runs indefinitely or until the task is completed.
2. Blocks JVM shutdown until they complete execution.
3. Examples include threads handling file operations, network communication, or main business logic.

**Example:**
```java
public class UserThreadExample {
    public static void main(String[] args) {
        Thread userThread = new Thread(() -> {
            System.out.println("User thread running...");
            try {
                Thread.sleep(3000); // Simulates work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("User thread finished.");
        });
        userThread.start();
    }
}
```

### **Daemon Thread**
- A **Daemon Thread** is a background thread that provides services to user threads.
- JVM will terminate the daemon thread automatically once all user threads finish execution.
- These threads are used for tasks like garbage collection or background monitoring.
- You can make a thread a daemon thread by calling `setDaemon(true)` before starting the thread.

**Key Characteristics:**
1. Runs in the background and does not block JVM shutdown.
2. Used for supporting tasks, not critical to application functionality.
3. Examples include garbage collection and thread pooling.

**Example:**
```java
public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread running...");
                try {
                    Thread.sleep(1000); // Simulates background work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemonThread.setDaemon(true); // Setting as a daemon thread
        daemonThread.start();

        System.out.println("Main thread running...");
        try {
            Thread.sleep(3000); // Main thread simulates work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread finished.");
    }
}
```
**Output:**

- The daemon thread will terminate once the main (user) thread completes execution.


### **Key Differences**
| **Aspect**          | **User Thread**                | **Daemon Thread**              |
|----------------------|--------------------------------|---------------------------------|
| **Purpose**          | Performs primary tasks.       | Supports user threads.         |
| **JVM Shutdown**     | JVM waits for its completion. | JVM does not wait for it.      |
| **Default Type**     | `main` thread is user thread. | Background threads like GC.    |
| **Lifecycle**        | Exists until explicitly stopped or finishes. | Ends when all user threads finish. |
| **Usage**            | Business logic, main processes. | Garbage collection, monitoring tasks. |

