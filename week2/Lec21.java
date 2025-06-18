package week2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Lec21 {
    public static void main(String[] args)
    {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                3,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),
                (Runnable r, ThreadPoolExecutor executor)-> {
                    System.out.println("Task Rejected");
                }
        );
        threadPoolExecutor.submit(new SleepingTask(1));
        threadPoolExecutor.submit(new SleepingTask(2));
        System.out.println("[1] Pool Size: " + threadPoolExecutor.getPoolSize());
        threadPoolExecutor.submit(new SleepingTask(3));
        threadPoolExecutor.submit(new SleepingTask(4));
        threadPoolExecutor.submit(new SleepingTask(5));

        System.out.println("[2] Pool Size: " + threadPoolExecutor.getPoolSize());
        threadPoolExecutor.submit(new SleepingTask(6));
        threadPoolExecutor.submit(new SleepingTask(7));


    }
    static class SleepingTask implements Runnable
    {
        private final int id;

        public SleepingTask(int id)
        {
            this.id = id;
        }
        @Override
        public void run()
        {
            try {
                Thread.sleep(200000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
