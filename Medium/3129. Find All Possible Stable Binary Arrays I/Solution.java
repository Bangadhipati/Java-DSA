class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        
        // dp[i][j][0] -> ends with 0
        // dp[i][j][1] -> ends with 1
        long[][][] dp = new long[zero + 1][one + 1][2];
        
        // Base cases: purely 0s or purely 1s up to the limit
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int j = 1; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }
        
        // DP Iteration
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                
                // Calculate ways to end with a block of 0s
                for (int k = 1; k <= Math.min(i, limit); k++) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - k][j][1]) % MOD;
                }
                
                // Calculate ways to end with a block of 1s
                for (int k = 1; k <= Math.min(j, limit); k++) {
                    dp[i][j][1] = (dp[i][j][1] + dp[i][j - k][0]) % MOD;
                }
            }
        }
        
        // Total stable arrays is the sum of those ending in 0 and those ending in 1
        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}