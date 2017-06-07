/**
 * Created by Ashish.Am.Singh on 07-06-2017.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    int N ,T;
    Percolation percolationModel ;
    double values [] ;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        N = n;
        T = trials;
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("Arguments out of bound");

        values = new double [T];
        double count = 0 ;
        for (int i = 0; i < trials; i++) {
            percolationModel = new Percolation(N);
            while (!percolationModel.percolates()) {
                int row = StdRandom.uniform(1, N+1);
                int col = StdRandom.uniform(1, N+1);
                if (percolationModel.isOpen(row, col)) {
                    continue;
                } else {
                    percolationModel.open(row, col);
                    count++ ;
                }
            }
            values[i]= (double) count / (N * N );
            count = 0 ;
        }

    }
    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(values);
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(values);
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - halfInterval();
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + halfInterval();
    }

    private double halfInterval (){
        return 1.96 * stddev()/Math.sqrt(values.length);
    }
    public static void main(String[] args)  {      // test client (described below)

        PercolationStats pls = new PercolationStats(Integer.parseInt("200"),
                Integer.parseInt("100"));

        System.out.printf("mean                     = %f\n", pls.mean());
        System.out.printf("stddev                   = %f\n", pls.stddev());
        System.out.printf("95%% confidence Interval  = %f, %f\n",
                pls.confidenceLo(), pls.confidenceHi());
    }
}
