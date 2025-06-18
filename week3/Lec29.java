package week3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lec29 {
    private static List<Lock> forks = new ArrayList<>();


    public static void main(String[] args)
    {
        for(int i=0;i<5;i++)
        {
            forks.add(new ReentrantLock());
        }

        Semaphore semaphore = new Semaphore(4);

        for(int i=0;i<5;i++)
        {
            new Thread(new Philosopher(i,semaphore)).start();
        }
    }

    static class Philosopher implements Runnable
    {
        private final int id;
        private Semaphore semaphore;
        public Philosopher(int id, Semaphore semaphore)
        {
            this.id = id;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            while(true)
            {
                think();
                pick_forks();
                eat();
                put_fork();
            }
        }
        void pick_forks()
        {
            try {
                semaphore.acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            forks.get(id).lock();
            System.out.println("Philsopher " + id + "picked the right fork");
            forks.get((id+1)%5).lock();
            System.out.println("Philsopher " + id + "picked the left fork");

        }
        void put_fork()
        {
            forks.get(id).unlock();
            forks.get((id+1)%5).unlock();
            semaphore.release();
        }



        void think()
        {
            System.out.println("Philospher" + id + " thinks");
        }
        void eat()
        {
            System.out.println("Philospher" + id + "eats");
        }
    }
}
