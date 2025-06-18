package week1;
import java.util.ArrayList;
import java.util.List;

public class Lec6 {
    private static int globalCounter = 0;
    private static final Object obj = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new myThread());

        List<Thread> threads = new ArrayList<>();
        ThreadGroup group = new ThreadGroup("Group1");

        for(int i=1;i<=1000;i++)
        {
            Thread t = new Thread(group,new myThread());
            t.start();
            threads.add(t);
        }

        group.interrupt();
        
        threads.forEach(t-> {
            try{
                t.join();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        System.out.println("Total = "+ globalCounter);
    }

    static class myThread implements Runnable
    {
        @Override
        public void run()
        {
            try {
                Thread.sleep(99999);
            } catch (InterruptedException e) {
            //    e.printStackTrace();
            }
            synchronized(obj)
            {
            globalCounter++;
            }
        }
    }
    // private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(()->"initialValue");
    // public static void main(String[] args) {
    //     Thread t1 = new Thread(new myThread());
    //     Thread t2 = new Thread(new myThread());

    //     t1.start();
    //     t2.start();
    // }
    // static class myThread implements Runnable
    // {
    //     @Override
    //     public void run()
    //     {
    //         int counter = 0;

    //         threadLocal.set("myValue");
    //         threadLocal.get();

    //     }
    // }
    
}
