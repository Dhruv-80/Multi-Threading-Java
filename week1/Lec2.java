package week1;
import java.util.*;
import java.lang.Thread;
public class Lec2 {
    // public static void main(String[] args) {
    //     Thread thread1 = new Thread(()-> {
    //         Thread currentThread = Thread.currentThread();
    //         System.out.println(currentThread.getName() + "priority = " + currentThread.getPriority());
    //     });

    //     thread1.setName("Thread_1");
    //     thread1.setPriority(Thread.MAX_PRIORITY);

    //     Thread thread2 = new Thread(()-> {
    //         Thread currentThread = Thread.currentThread();
    //         System.out.println(currentThread.getName() + "priority = " + currentThread.getPriority());
    //     });

    //     thread2.setName("Thread_2");
    //     thread2.setPriority(Thread.MIN_PRIORITY);

    //     thread1.start();
    //     thread2.start();

    //     try{
    //         thread1.join();
    //     }
    //     catch(Exception e)
    //     {
    //         System.err.println("Cant join t1");
    //     }
    //     try {
    //         thread2.join();
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
        
    // }
    public static void main(String[] args) {
        Thread thread = new Thread(()->
        {
            Thread currentThread = Thread.currentThread();
            // Runnable
            System.out.println("[1] State: =" + currentThread.getState() );
        });
        // New
        System.out.println("[2] State: =" + thread.getState());
        thread.start();
        // Runnable
        System.out.println("[3] State: =" + thread.getState());
        try{
            thread.join();
        }
        catch(Exception e )
        {
            e.printStackTrace();;
        }
        // Terminated
        System.out.println("[4] State: =" + thread.getState());


    }
}
