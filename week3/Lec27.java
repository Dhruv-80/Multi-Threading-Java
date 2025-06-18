package week3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lec27 {
    private static int N = 10;

    private static int[][] A = new int[N][N];
    private static int[][] B = new int[N][N];
    private static int[][] C = new int[N][N];

    public static void main(String args[]) throws InterruptedException {
        initMatrixes();

        printMatrix(A);
        System.out.println("");
        printMatrix(B);
        long start = System.nanoTime();

        //multiplyParallel();
        multiplySerial();

        long end = System.nanoTime();



        System.out.println("");
        printMatrix(C);
        System.out.println("Execution Time: " + (end-start)/1_000_000_000.0);
    }
    static void multiplyParallel() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);

        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                service.submit(new MultiplyTask(i,j));
            }
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

    }

    static class MultiplyTask implements Runnable
    {
        private final int line;
        private final int col;
        public MultiplyTask(int line, int col)
        {
            this.line = line;
            this.col = col;
        }

        @Override
        public void run()
        {
            C[line][col] = 0;
            for(int k=0;k<N;k++)
            {
                C[line][col]+=A[line][k]*B[k][col];
            }
        }
    }



    static void multiplySerial()
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                C[i][j] = 0;
                for(int k=0;k<N;k++)
                {
                    C[i][j]+=A[i][k]*B[k][j];
                }
            }
        }

    }

    static void initMatrixes()
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                A[i][j] = 1;
                B[i][j]=1;
            }
        }
    }
    static void checkResult()
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(C[i][j] != N)
                {
                    System.out.println("Wrong result");
                }
            }
        }

    }
    static void printMatrix(int[][] matrix)
    {
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
            System.out.print(matrix[i][j] + " ");
        }
        System.out.println("");
    }
    }
}
