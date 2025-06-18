package week2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Lec19 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2,
            2,
            1,
            TimeUnit.MINUTES,
            new SynchronousQueue<>()
        );


        threadPoolExecutor.submit(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Task 1");

        threadPoolExecutor.submit(()->
        {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Task 2");

        threadPoolExecutor.submit(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Task 3");
    }
}
