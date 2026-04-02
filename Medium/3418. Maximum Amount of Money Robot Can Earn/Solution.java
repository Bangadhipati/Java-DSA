class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        // dp[i][j][k] -> Max money at (i, j) having used exactly k neutralizations
        int[][][] dp = new int[m][n][3];
        
        int MIN_VAL = -1000000000;
        
        // Initialize all states to a safely small negative number
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = MIN_VAL;
                dp[i][j][1] = MIN_VAL;
                dp[i][j][2] = MIN_VAL;
            }
        }
        
        // Base case: Starting cell (0, 0)
        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0; // Use 1 neutralization immediately on the first cell
        }
        
        // Traverse the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // Skip the starting cell as it's already processed
                if (i == 0 && j == 0) continue;
                
                // Evaluate all 3 possible states of neutralizations used
                for (int k = 0; k < 3; k++) {
                    
                    // 1. Calculate the max if we DON'T use a neutralization on THIS cell
                    int maxPrev = MIN_VAL;
                    if (i > 0) maxPrev = Math.max(maxPrev, dp[i - 1][j][k]);
                    if (j > 0) maxPrev = Math.max(maxPrev, dp[i][j - 1][k]);
                    
                    if (maxPrev != MIN_VAL) {
                        dp[i][j][k] = Math.max(dp[i][j][k], maxPrev + coins[i][j]);
                    }
                    
                    // 2. Calculate the max if we DO use a neutralization on THIS cell
                    // We can only do this if it's a robber (< 0) and we have charges left (k > 0)
                    if (k > 0 && coins[i][j] < 0) {
                        int maxPrevNeut = MIN_VAL;
                        if (i > 0) maxPrevNeut = Math.max(maxPrevNeut, dp[i - 1][j][k - 1]);
                        if (j > 0) maxPrevNeut = Math.max(maxPrevNeut, dp[i][j - 1][k - 1]);
                        
                        if (maxPrevNeut != MIN_VAL) {
                            dp[i][j][k] = Math.max(dp[i][j][k], maxPrevNeut); // Add 0 instead of taking loss
                        }
                    }
                }
            }
        }
        
        // The answer is the absolute best outcome at the bottom-right corner, 
        // regardless of whether we used 0, 1, or 2 neutralizations.
        return Math.max(dp[m - 1][n - 1][0], Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}