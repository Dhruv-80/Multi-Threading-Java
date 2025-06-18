package week1;
import java.util.*;
import java.lang.Thread;
public class Lec1{
    // public static void main(String[] args) {
    //     Thread thread = Thread.currentThread();
    //     System.out.println("Current Thread: " + thread.getName());
    //     try{
    //         Thread.sleep(5000);
    //     }
    //     catch (Exception e)
    //     {
    //         System.out.println("Thread is in sleep");
    //     }
        

    //     System.out.println("Current Thread: " + thread.getName());
    // }
    // public static void main(String[] args) {
    //     //Extending the thread class
    //     // Runnable interface

    //     MyThread myThread = new MyThread();
    //     myThread.start();
    // }
    // static class MyThread extends Thread{
    //     public void run()
    //     {
    //         setName("MyThread");
    //         System.out.println("Current Thread: " + Thread.currentThread().getName());
    //     }
    // }

    public static void main(String[] args) {
        System.out.println("[1] Current Thread: " + Thread.currentThread().getName());
        // Runnable runnable = new Runnable() {
        //     @Override
        //     public void run()
        //     {
        //         System.out.println("[2] Current Thread: " + Thread.currentThread().getName());
        //     }
        // };
        Thread thread = new Thread(
            () -> {
                System.out.println("[2] Current Thread: " + Thread.currentThread().getName());
            }
        );
        thread.setName("MyThread");
        thread.start();
        try{
            //it makes sure that previous thread is completed before the next thread starts            
            thread.join();
        }
        catch ( Exception e)
        {
            System.out.println("Join exception");
        }
        System.out.println("[3] Current Thread: " + Thread.currentThread().getName());
    }
}
