package week2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Lec18 {
    public static void main(String[] args) throws InterruptedException {
     ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        3,
        5,
        1,
        TimeUnit.MINUTES,
        new ArrayBlockingQueue<>(3)
        );

            threadPoolExecutor.prestartAllCoreThreads();

            threadPoolExecutor.execute(()-> System.out.println("Task 1"));
            threadPoolExecutor.execute(()-> System.out.println("Task 2"));

            System.out.println("Pool Size: " + threadPoolExecutor.getPoolSize());

            threadPoolExecutor.shutdown();
            threadPoolExecutor.awaitTermination(3, TimeUnit.SECONDS);

            //threadPoolExecutor.submit(new CallableTask());

            // Future<Integer> future = threadPoolExecutor.submit(new CallableTask());

            // future.get();
    }
    static class CallableTask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception
        {
            return 4;
        }
    }


}
