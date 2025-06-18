package week2;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

public class Lec24 {
    private static int[] array = {1,2,3,4,5,6,7,8};
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(2);

       Future<?> future =  pool.submit(new IncrementTask(0,8));
       future.get();
       System.out.println("The array is: " + Arrays.toString(array));

    }
    static class IncrementTask extends RecursiveAction
    {
        private int left;
        private int right;
        public IncrementTask(int left, int right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute()
        {
            if(right - left < 2)
            {
                for(int i = left; i< right ; i++)
                {
                    array[i]++;
                }
            }
            else
            {
                int mid = (left + right)/2;
                invokeAll(new IncrementTask(left ,mid),new IncrementTask(mid,right));

            }
        }
    }
}
