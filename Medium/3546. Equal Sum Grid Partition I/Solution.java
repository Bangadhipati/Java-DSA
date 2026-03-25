class Solution {
    // Renamed to match the platform's exact expected signature
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 1. Project the grid into 1D row and column sums
        // MUST use long to prevent integer overflow on large grids
        long[] rowSums = new long[m];
        long[] colSums = new long[n];
        long totalSum = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                rowSums[i] += val;
                colSums[j] += val;
                totalSum += val;
            }
        }
        
        // 2. If the total sum is odd, an equal partition is impossible
        if (totalSum % 2 != 0) {
            return false;
        }
        
        long target = totalSum / 2;
        long currentSum = 0;
        
        // 3. Check for a valid horizontal cut (row prefixes)
        // We iterate up to m - 1 because a cut must leave at least 1 row on the bottom
        for (int i = 0; i < m - 1; i++) {
            currentSum += rowSums[i];
            if (currentSum == target) {
                return true;
            }
        }
        
        currentSum = 0;
        
        // 4. Check for a valid vertical cut (column prefixes)
        // We iterate up to n - 1 because a cut must leave at least 1 column on the right
        for (int j = 0; j < n - 1; j++) {
            currentSum += colSums[j];
            if (currentSum == target) {
                return true;
            }
        }
        
        // If neither cut works, return false
        return false;
    }
}