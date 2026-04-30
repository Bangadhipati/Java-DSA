import java.util.Arrays;

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // The maximum possible cost for any path is strictly bounded by the path length.
        // We cap 'maxCost' to prevent allocating memory for unnecessarily large 'k'.
        int maxCost = Math.min(k, m + n);
        
        // dp[c][cost] stores the max score to reach the current row, column c, with exact 'cost'
        int[][] dp = new int[n][maxCost + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        
        // The problem guarantees grid[0][0] == 0, so the starting cost and score are both 0.
        dp[0][0] = 0;
        
        for (int r = 0; r < m; r++) {
            // Create a new DP array for the current row to save memory
            int[][] nextDp = new int[n][maxCost + 1];
            for (int[] arr : nextDp) {
                Arrays.fill(arr, -1);
            }
            
            for (int c = 0; c < n; c++) {
                int cellCost = grid[r][c] == 0 ? 0 : 1;
                int cellScore = grid[r][c];
                
                // Try every possible valid cost up to our cap
                for (int cost = cellCost; cost <= maxCost; cost++) {
                    int prevCost = cost - cellCost;
                    int best = -1;
                    
                    // Route 1: From the top (r - 1)
                    if (r > 0 && dp[c][prevCost] != -1) {
                        best = Math.max(best, dp[c][prevCost] + cellScore);
                    }
                    
                    // Route 2: From the left (c - 1)
                    if (c > 0 && nextDp[c - 1][prevCost] != -1) {
                        best = Math.max(best, nextDp[c - 1][prevCost] + cellScore);
                    }
                    
                    // Anchor the starting cell (0, 0)
                    if (r == 0 && c == 0 && cost == 0) {
                        best = 0;
                    }
                    
                    nextDp[c][cost] = best;
                }
            }
            // Move to the next row
            dp = nextDp;
        }
        
        // Scan the destination cell (n - 1) to find the absolute maximum score across all valid costs
        int maxScore = -1;
        for (int cost = 0; cost <= maxCost; cost++) {
            if (dp[n - 1][cost] != -1) {
                maxScore = Math.max(maxScore, dp[n - 1][cost]);
            }
        }
        
        return maxScore;
    }
}