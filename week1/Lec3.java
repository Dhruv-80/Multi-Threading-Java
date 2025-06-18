package week1;
public class Lec3 {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("Group1");

        ThreadGroup parent = group1.getParent();
        System.out.println("Parent name: " + parent.getName() + " Priority: " + parent.getMaxPriority());


        Thread thread1 = new Thread(group1, new myThread(), "Thread1");
        Thread thread2 = new Thread(group1, new myThread(), "Thread2");
        Thread thread3 = new Thread(group1, new myThread(), "Thread3");




        thread1.start();
        thread2.start();
        thread3.start();


        System.out.println("Sleeping for three seconds");
        Thread.sleep(3000);
        // group1.setMaxPriority(Thread.NORM_PRIORITY);
        group1.interrupt();

    }   
    static class myThread implements Runnable{
        @Override
        public void run()
        {
            while(true)
            {
                try{
                    Thread.sleep(3000);
                }
                catch(InterruptedException e)
                {
                    Thread currentThread = Thread.currentThread();
                    System.out.println("Name : "+ currentThread.getName() + "priority = " + currentThread.getPriority());
                }
            }
        }
    } 
}
