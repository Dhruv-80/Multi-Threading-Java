package week3;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

public class MergeSort {
    public static int[] nums = {1,42,1,5,26,2,6,1,99,7,32,7,2};
    public static int n = nums.length;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       // Sort(nums,0,n-1);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<?> future = forkJoinPool.submit(new MergeExecutor(nums,0,n-1));
        future.get();
        for(int i: nums)
        {
            System.out.println(i);
        }
    }
    static class MergeExecutor extends RecursiveAction{

        private final int[] arr;
        private final int low;
        private final int high;

        public MergeExecutor (int[] arr, int low, int high)
        {
            this.arr = arr;
            this.low = low;
            this.high = high;
        }
        @Override
        public void compute()
        {
            if(low>=high)
            {
             return;
            }
            int mid = (low+high)/2;
            invokeAll(new MergeExecutor(nums,low,mid),new MergeExecutor(nums,mid+1,high));
            Merge(nums,low,mid,high);
        }
    }
//    public static void Sort(int[] nums,int low, int high)
//    {
//        if(low>=high)
//        {
//            return;
//        }
//        int mid = (low+high)/2;
//        Sort(nums,low,mid);
//        Sort(nums,mid+1,high);
//        Merge(nums,low,mid,high);
//    }
public static void Merge(int[] nums, int low, int mid, int high) {
    int left = low;
    int right = mid + 1;
    ArrayList<Integer> temp = new ArrayList<>();

    // Corrected loop: left should not go beyond mid
    while (left <= mid && right <= high) {
        if (nums[left] <= nums[right]) {
            temp.add(nums[left++]);
        } else {
            temp.add(nums[right++]);
        }
    }

    while (left <= mid) {
        temp.add(nums[left++]);
    }

    while (right <= high) {
        temp.add(nums[right++]);
    }

    for (int i = low; i <= high; i++) {
        nums[i] = temp.get(i - low);
    }
}

}
