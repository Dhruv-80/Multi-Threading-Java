package week2;

import java.util.concurrent.*;

import javax.management.RuntimeErrorException;

public class Lec20 {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                2,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2)
        );
        Future<?> future = threadPoolExecutor.submit(()->{
            throw new RuntimeErrorException(null, "test exception");
        });

//        try{
//            future.get();
//        }
//        catch(InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//        catch(ExecutionException e)
//        {
//            e.printStackTrace();
//        }

    }
    static class CustomThreadPoolExecutor extends ThreadPoolExecutor
    {

        public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                        BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);

            if(t!=null)
            {
                System.out.println(t);
            }
        }
    }

}
