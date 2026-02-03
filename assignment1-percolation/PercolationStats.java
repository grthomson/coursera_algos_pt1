import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Runs Monte Carlo simulations to estimate the percolation threshold.
 *
 * The percolation threshold is the fraction of open sites when the system first percolates.
 *
 * Statistical summary:
 *  - mean(): average threshold across T experiments
 *  - stddev(): sample standard deviation of thresholds
 *  - confidenceLo()/confidenceHi(): 95% confidence interval using
 *      mean ± 1.96 * stddev / sqrt(T)
 */
public class PercolationStats {

    // 95% confidence constant for normal distribution
    private static final double CONFIDENCE_95 = 1.96;

    private final int trials;
    private final double[] thresholds; // threshold per experiment

    /**
     * Perform T independent experiments on an N-by-N grid.
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size n must be > 0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("Number of trials must be > 0");
        }

        this.trials = trials;
        this.thresholds = new double[trials];

        // Run the experiments
        for (int t = 0; t < trials; t++) {
            thresholds[t] = runExperiment(n);
        }
    }

    /**
     * Runs one experiment:
     *  - start with all sites blocked
     *  - open random blocked sites until percolation
     *  - return fraction opened at percolation time
     */
    private double runExperiment(int n) {
        Percolation perc = new Percolation(n);

        while (!perc.percolates()) {
            // StdRandom.uniformInt(a, b) exists in newer versions;
            // uniform(n) + 1 works across typical algs4 versions.
            int row = StdRandom.uniform(n) + 1;
            int col = StdRandom.uniform(n) + 1;

            // open() is idempotent in our Percolation, so we could just call perc.open(row, col).
            // But checking isOpen avoids extra union/find operations when repeats occur.
            if (!perc.isOpen(row, col)) {
                perc.open(row, col);
            }
        }

        // Threshold = open sites / total sites
        return perc.numberOfOpenSites() / (double) (n * n);
    }

    /**
     * Sample mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * Sample standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * Low endpoint of 95% confidence interval.
     */
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    /**
     * High endpoint of 95% confidence interval.
     */
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    /**
     * Standard assignment main method: read N and T from command-line args.
     *
     * Example:
     *   java PercolationStats 200 100
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        System.out.printf("mean                    = %.16f%n", stats.mean());
        System.out.printf("stddev                  = %.16f%n", stats.stddev());
        System.out.printf("95%% confidence interval = [%.16f, %.16f]%n",
                          stats.confidenceLo(), stats.confidenceHi());
    }
}
