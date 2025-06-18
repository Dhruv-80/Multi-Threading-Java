package week2;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Lec23 {
    public static void main(String[] args)
    {
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(3);

        ScheduledFuture<?> future = threadPoolExecutor.scheduleAtFixedRate(()->
            System.out.println("Task"),
            5,
            1,
            TimeUnit.SECONDS
        );

        future.cancel(true);
    }
}
