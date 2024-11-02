package hw2.hw2;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private Percolation tst;
    private PercolationFactory pf;
    private double mu;
    private double sigma;
    private int T;
    private int N;
    private double[] x;

    private void startTest(){
        double sum = 0;
        for(int i = 0; i < T; i++){
            int times = 0;
            Percolation p = pf.make(N);
            while(!tst.percolates()){
                int rand1 = StdRandom.uniform(N);
                int rand2 = StdRandom.uniform(N);
                if(!p.isOpen(rand1, rand2)){
                    ++times;
                    tst.open(rand1, rand2);
            }
        }
            double xi = (double) times / (N * N);
            sum += xi;
            x[i] = xi;
        }
        mu = sum / T;
    }

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        this.N = N;
        this.T = T;
        this.pf = pf;
        x = new double[T];
        double s = 0;
        startTest();
        for(int i = 0; i < T; i++){
            s += (x[i] - mu) * (x[i] - mu);
        }
        sigma = Math.sqrt(s / (T - 1));
    }
    // sample mean of percolation threshold
    public double mean(){
        return mu;
    }
    // sample standard deviation of percolation threshold
    public double stddev(){
        return sigma;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return (mu - 1.96*sigma / Math.sqrt(T));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return (mu + 1.96*sigma / Math.sqrt(T));
    }
}
