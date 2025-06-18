package week3;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

public class Lec26 {

    private static Random random = new Random();
    private static int arraySize = 1000000;
    private static int[] array = new int[arraySize];



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for(int i=0;i<arraySize;i++)
        {
            array[i] = random.nextInt(arraySize);
        }
       // System.out.println(Arrays.toString(array));

    long start = System.nanoTime();
        quickSort(0,arraySize -1);
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        Future<Void> future = forkJoinPool.submit(new QuickSortTask(0,arraySize-1));
//        future.get();
        long end = System.nanoTime();

        System.out.println("Execution time = " + (end - start)/1_000_000_000.0);

        //   System.out.println(Arrays.toString(array));
    }

    static class QuickSortTask extends RecursiveAction {
        private final int left;
        private final int right;

        public QuickSortTask(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute()
        {
            if(left<right)
            {
                int pivotIndex = partition(left,right);
                invokeAll(new QuickSortTask(left, pivotIndex-1), new QuickSortTask(pivotIndex+1,right));

            }
        }

    }


    static int partition(int left, int right)
    {
        int pivot = array[right];
        int swapIndex = left - 1;

        for(int i=left;i<right;i++)
        {
            if(array[i]<pivot)
            {
                swapIndex++;
                swap(swapIndex,i);
            }
        }

        swapIndex++;
        swap(swapIndex,right);

        return swapIndex;
    }

    static void quickSort(int left, int right)
    {
        if(left<right)
        {
            int pivotIndex = partition(left,right);

            quickSort(left, pivotIndex-1);
            quickSort(pivotIndex+1,right);
        }

    }

    static void swap(int leftIndex, int rightIndex)
    {
        int aux = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = aux;
    }

}
