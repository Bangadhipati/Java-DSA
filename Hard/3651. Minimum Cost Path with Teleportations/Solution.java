import java.util.*;

class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        
        // Initialize DP with a large value
        for (int[] row : dp) Arrays.fill(row, 1_000_000_000);
        dp[0][0] = 0;

        // We only need to store the best cost for each possible grid value (0 to 10000)
        // This is much faster than sorting every time.
        int[] minCostByValue = new int[10001];

        for (int t = 0; t <= k; t++) {
            // 1. Pathfinding (Standard DP: Down and Right)
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + grid[i][j]);
                    if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + grid[i][j]);
                }
            }

            if (t == k) break; // No more teleports allowed

            // 2. Teleportation Preparation (Suffix Minimum)
            Arrays.fill(minCostByValue, 1_000_000_000);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    minCostByValue[grid[i][j]] = Math.min(minCostByValue[grid[i][j]], dp[i][j]);
                }
            }

            // Suffix Min: minCostByValue[v] will store min cost to reach any cell with value >= v
            for (int v = 9999; v >= 0; v--) {
                minCostByValue[v] = Math.min(minCostByValue[v], minCostByValue[v + 1]);
            }

            // 3. Apply Teleportation for the next layer
            // Every cell (i, j) can be reached via teleport from a cell in the current layer
            // that has gridValue >= grid[i][j] with cost 0.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Math.min(dp[i][j], minCostByValue[grid[i][j]]);
                }
            }
        }

        return dp[m - 1][n - 1] >= 1_000_000_000 ? -1 : dp[m - 1][n - 1];
    }
}