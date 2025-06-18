package week2;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

public class Lec14 {

   private static int S = 0;
private static int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
private static Phaser phaser = new Phaser(1); // Register main thread

public static void main(String[] args) {
    int n = array.length;

    // Spawn worker threads
    for (int i = 0; i < n; i++) {
        Thread t = new Thread(new WorkerThread(i));
        t.start();
    }

    // Wait for phase 0 (computation) to complete
    phaser.arriveAndAwaitAdvance();

    // Wait for phase 1 (sum computation) to complete
    phaser.arriveAndAwaitAdvance();

    // Print result
    System.out.println("The sum is: " + S);
    System.out.println("Phase count: " + phaser.getPhase());
}

static class WorkerThread implements Runnable {
    private final int threadIndex;

    public WorkerThread(int threadIndex) {
        this.threadIndex = threadIndex;
        phaser.register();
    }

    @Override
    public void run() {
        // Perform safe computation
        if (threadIndex * 2 < array.length) {
            array[threadIndex] = array[threadIndex * 2];
        } else {
            array[threadIndex] = 0; // Or leave it unchanged
        }

        phaser.arriveAndAwaitAdvance(); // Wait for all threads to complete phase 0

        if (threadIndex == 0) {
            // Only one thread computes the sum
            int sum = 0;
            for (int value : array) {
                sum += value;
            }
            S = sum;
            phaser.arriveAndAwaitAdvance(); // Signal phase 1 completion
        } else {
            phaser.arriveAndDeregister(); // Exit after phase 0
        }
    }
}
}
