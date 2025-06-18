package week2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Lec22 {
    public static void main(String[] args)
    {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
          2,
          3,
          1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(5),
        (Runnable r,  ThreadPoolExecutor t) ->
    {
        //System.out.println("Rejected");
    }
        );
        threadPoolExecutor.getPoolSize();
        threadPoolExecutor.getActiveCount();
        threadPoolExecutor.getTaskCount();
        threadPoolExecutor.getCompletedTaskCount();
    }
}
