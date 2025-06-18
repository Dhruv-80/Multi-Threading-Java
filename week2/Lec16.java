package week2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lec16 {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();
    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            lock1.lock();
            System.out.println("Thread 1 acquired lock1");

            lock2.lock();
            System.out.println("Thread 1 acquired lock2");

            lock2.unlock();
            lock1.unlock();

        });

        Thread t2 = new Thread(()->{
            lock2.lock();
            System.out.println("Thread 2 acquired lock2");

            lock1.lock();
            System.out.println("Thread 2 acquired lock1");

            lock1.unlock();
            lock2.unlock();
        });

        t1.start();
        t2.start();
    }
}
