package week2;

import java.util.concurrent.Exchanger;

public class Lec15 {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        Thread t = new Thread(()->{
            try{
            String recievedValue = exchanger.exchange("value1");
            System.out.println("Recieved: " + recievedValue + "in thread " + Thread.currentThread().getName());
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        t.start();

        
        String recievedValue = exchanger.exchange("value2");
        System.out.println("Recieved: " + recievedValue + "in thread " + Thread.currentThread().getName());
    }
}
