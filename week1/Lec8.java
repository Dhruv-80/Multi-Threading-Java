package week1;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lec8 {
    private static Lock lockObject = new ReentrantLock();
    private static int[] array = new int[10];
    private static int S = 0; 
    public static void main(String[] args) {
         
        for(int i=0;i<10;i++)
        {
            array[i] = 10;
        }

        List<Thread> threadList = new ArrayList<>();
        int threadSlice = array.length/2;
        for(int i=0;i<2;i++)
        {
            Thread t = new Thread(new WorkerThread(i*threadSlice, (i+1)*threadSlice));
            t.start();
            threadList.add(t);
        }
        
        threadList.forEach(t-> {
            try{
                t.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        System.out.println("the sum is : " + S);
        
    }
    static class WorkerThread implements Runnable
    {
        private final int left;
        private final int right;

        public WorkerThread(int left, int right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public void run()
        {
            for(int i=left;i<right;i++)
            {
                lockObject.lock();
                S = S + array[i];
                lockObject.unlock();
            }
        }
    }
}

