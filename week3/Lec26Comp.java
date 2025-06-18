package week3;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

public class Lec26Comp {

    private static Random random = new Random();
    private static int arraySize = 100000000;
    private static int[] array = new int[arraySize];



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Initialize the array with random values
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(arraySize);
        }

        // Clone the array for fair comparison
        int[] arrayForParallel = Arrays.copyOf(array, arraySize);
        int[] arrayForSerial = Arrays.copyOf(array, arraySize);

        // Measure parallel quicksort (ForkJoinPool)
        long startParallel = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Void> future = forkJoinPool.submit(new QuickSortTask(arrayForParallel, 0, arraySize - 1));
        future.get();
        long endParallel = System.nanoTime();
        System.out.println("Parallel QuickSort time: " + (endParallel - startParallel) / 1_000_000_000.0 + " seconds");

        // Measure regular recursive quicksort
        long startSerial = System.nanoTime();
        quickSort(arrayForSerial, 0, arraySize - 1);
        long endSerial = System.nanoTime();
        System.out.println("Serial QuickSort time: " + (endSerial - startSerial) / 1_000_000_000.0 + " seconds");
    }


    static class QuickSortTask extends RecursiveAction {
        private final int[] array;
        private final int left;
        private final int right;

        public QuickSortTask(int[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left < right) {
                int pivotIndex = partition(array, left, right);
                invokeAll(
                        new QuickSortTask(array, left, pivotIndex - 1),
                        new QuickSortTask(array, pivotIndex + 1, right)
                );
            }
        }
    }


    static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int swapIndex = left - 1;
        for (int i = left; i < right; i++) {
            if (array[i] < pivot) {
                swapIndex++;
                swap(array, swapIndex, i);
            }
        }
        swapIndex++;
        swap(array, swapIndex, right);
        return swapIndex;
    }

    static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, right);
        }
    }

    static void swap(int[] array, int leftIndex, int rightIndex) {
        int aux = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = aux;
    }


}
