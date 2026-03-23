class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Track both the max and min products to handle negative sign flips
        long[][] maxProd = new long[m][n];
        long[][] minProd = new long[m][n];
        
        // Base Case: Top-left cell
        maxProd[0][0] = grid[0][0];
        minProd[0][0] = grid[0][0];
        
        // 1. Initialize the first row (can only come from the left)
        for (int j = 1; j < n; j++) {
            maxProd[0][j] = maxProd[0][j - 1] * grid[0][j];
            minProd[0][j] = minProd[0][j - 1] * grid[0][j];
        }
        
        // 2. Initialize the first column (can only come from above)
        for (int i = 1; i < m; i++) {
            maxProd[i][0] = maxProd[i - 1][0] * grid[i][0];
            minProd[i][0] = minProd[i - 1][0] * grid[i][0];
        }
        
        // 3. DP Traversal for the rest of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                
                // The 4 possible products coming from top and left
                long p1 = val * maxProd[i - 1][j]; // From top (max)
                long p2 = val * minProd[i - 1][j]; // From top (min)
                long p3 = val * maxProd[i][j - 1]; // From left (max)
                long p4 = val * minProd[i][j - 1]; // From left (min)
                
                // Find the new absolute max and min
                maxProd[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
                minProd[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
            }
        }
        
        // 4. Extract the final answer from the bottom-right corner
        long finalMax = maxProd[m - 1][n - 1];
        
        if (finalMax < 0) {
            return -1;
        }
        
        // Apply modulo only at the very end
        return (int) (finalMax % 1000000007);
    }
}