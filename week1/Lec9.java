package week1;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lec9 {

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    private static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        Thread writer = new Thread(new WriterThread());
        Thread thread1 = new Thread(new ReaderThread());
        Thread thread2 = new Thread(new ReaderThread());
        Thread thread3 = new Thread(new ReaderThread());
        Thread thread4 = new Thread(new ReaderThread());

        writer.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

    static class WriterThread implements Runnable
    {
        @Override
        public void run()
        {
            while(true)
            {
                try{
                writeData();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

        public void writeData() throws InterruptedException
        {
            Thread.sleep(3000);
            writeLock.lock();

            int value = (int) (Math.random() * 10);
            System.out.println("Producing data: " + value);

            list.add(value);

            writeLock.unlock();
        }
    }

    static class ReaderThread implements Runnable
    {
        @Override
        public void run()
        {
            while(true)
            {
            
            try
            {

                readData();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            }

        }

        public void readData() throws InterruptedException {
            Thread.sleep(3000);

            while(true)
            {
                boolean acquired = readLock.tryLock();
                if(acquired)
                {
                    break;
                }
                System.out.println("Waiting for read lock");
            }
            
            System.out.println("List is: "+ list);
            readLock.unlock();

        }
    }
}
