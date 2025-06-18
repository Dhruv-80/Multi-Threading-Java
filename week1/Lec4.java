package week1;
public class Lec4 {
    public static void main(String[] args) {
        // Thread thread = new Thread(()-> System.out.println("MyThread"));
        // thread.setDaemon(true);
        Thread thread1 = new Thread(new myThread(10),"Thread1");
        Thread thread2 = new Thread(new myThread(3),"Thread2");
        
        thread1.setDaemon(true);

        thread1.start();
        thread2.start();
    }

    static class myThread implements Runnable{
        private final int numberOfSeconds;

        public myThread(int numberOfSeconds)
        {
            this.numberOfSeconds = numberOfSeconds;
        }
        
        
        @Override
        public void run()
        {
            for(int i=0;i<numberOfSeconds;i++)
            {
                try{
                    System.out.println("Sleeping for 1s: " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
