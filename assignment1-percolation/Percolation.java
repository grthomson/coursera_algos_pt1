import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Models an N-by-N percolation system.
 *
 * API (typical Princeton assignment):
 *  - Percolation(int n)
 *  - void open(int row, int col)
 *  - boolean isOpen(int row, int col)
 *  - boolean isFull(int row, int col)
 *  - int numberOfOpenSites()
 *  - boolean percolates()
 *
 * Rows/cols are 1-indexed (1..N), as per the assignment.
 */
public class Percolation {
    private final int n;

    // open[i] tells us if site i is open (true) or blocked (false).
    // We store only real grid sites (1..n*n). Index 0 is unused here.
    private final boolean[] open;

    // Count of open sites (useful for PercolationStats).
    private int openCount;

    // Union-Find for percolation test: includes virtual top and bottom.
    private final WeightedQuickUnionUF ufPercolates;

    // Union-Find for fullness test: includes ONLY virtual top (prevents backwash).
    private final WeightedQuickUnionUF ufFullness;

    // Virtual sites (extra nodes not in the real grid)
    private final int virtualTop;
    private final int virtualBottom;

    /**
     * Creates an N-by-N grid, with all sites initially blocked.
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size n must be > 0");
        }

        this.n = n;
        this.open = new boolean[n * n + 1]; // 1..n*n used
        this.openCount = 0;

        // Virtual top is at index 0
        this.virtualTop = 0;

        // Virtual bottom is at index n*n + 1 (only used in ufPercolates)
        this.virtualBottom = n * n + 1;

        // ufPercolates needs space for: 0..n*n+1
        this.ufPercolates = new WeightedQuickUnionUF(n * n + 2);

        // ufFullness needs space for: 0..n*n (no virtual bottom)
        this.ufFullness = new WeightedQuickUnionUF(n * n + 1);
    }

    /**
     * Opens the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        validate(row, col);

        int index = toIndex(row, col);

        // Important: make open() idempotent.
        // Calling open() on an already-open site should do nothing.
        if (open[index]) {
            return;
        }

        open[index] = true;
        openCount++;

        // If it's in the top row, connect it to virtualTop in both UF structures.
        if (row == 1) {
            ufPercolates.union(index, virtualTop);
            ufFullness.union(index, virtualTop);
        }

        // If it's in the bottom row, connect it to virtualBottom (percolation UF only).
        if (row == n) {
            ufPercolates.union(index, virtualBottom);
        }

        // Connect to open neighbors (up, down, left, right).
        // Each check is O(1); each union/find is near-constant (amortized).
        connectIfOpen(row - 1, col, index); // up
        connectIfOpen(row + 1, col, index); // down
        connectIfOpen(row, col - 1, index); // left
        connectIfOpen(row, col + 1, index); // right
    }

    /**
     * Returns true if the site (row, col) is open.
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return open[toIndex(row, col)];
    }

    /**
     * Returns true if the site (row, col) is full.
     *
     * "Full" means: the site is open AND connected to the top via open sites.
     *
     * We check this using ufFullness, which has NO virtual bottom,
     * preventing the backwash issue.
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = toIndex(row, col);
        return open[index] && ufFullness.find(index) == ufFullness.find(virtualTop);
    }

    /**
     * Returns the number of open sites.
     */
    public int numberOfOpenSites() {
        return openCount;
    }

    /**
     * Returns true if the system percolates.
     *
     * Percolates means: virtualTop is connected to virtualBottom in ufPercolates.
     */
    public boolean percolates() {
        return ufPercolates.find(virtualTop) == ufPercolates.find(virtualBottom);
    }

    // ----------------- Helper methods -----------------

    /**
     * Convert (row, col) in 1..n to a 1D index in 1..n*n.
     *
     * Example for n=3:
     * (1,1)->1 (1,2)->2 (1,3)->3
     * (2,1)->4 (2,2)->5 (2,3)->6
     * (3,1)->7 (3,2)->8 (3,3)->9
     */
    private int toIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    /**
     * If (r,c) is in bounds AND open, union it with "currentIndex" in both UF structures.
     */
    private void connectIfOpen(int r, int c, int currentIndex) {
        if (r < 1 || r > n || c < 1 || c > n) {
            return; // neighbor is outside the grid
        }

        int neighborIndex = toIndex(r, c);
        if (open[neighborIndex]) {
            ufPercolates.union(currentIndex, neighborIndex);
            ufFullness.union(currentIndex, neighborIndex);
        }
    }

    /**
     * Validate that row and col are between 1 and n.
     * We use IllegalArgumentException (common for this assignment).
     */
    private void validate(int row, int col) {
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("row must be between 1 and " + n + ": " + row);
        }
        if (col < 1 || col > n) {
            throw new IllegalArgumentException("col must be between 1 and " + n + ": " + col);
        }
    }
}
