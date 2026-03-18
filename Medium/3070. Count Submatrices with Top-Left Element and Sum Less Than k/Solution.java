class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 1. Calculate the 2D prefix sum for the current cell
                if (i > 0) grid[i][j] += grid[i - 1][j];
                if (j > 0) grid[i][j] += grid[i][j - 1];
                if (i > 0 && j > 0) grid[i][j] -= grid[i - 1][j - 1];
                
                // 2. Check against the threshold k
                if (grid[i][j] <= k) {
                    count++;
                } else {
                    // 3. Early Exit Pruning
                    // Because all numbers are >= 0, moving right will only increase the sum.
                    // We can safely abandon the rest of this row.
                    break; 
                }
            }
        }
        
        return count;
    }
}