class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        
        // Using int instead of long saves ~8MB of memory and speeds up CPU caching.
        // Signed 32-bit int can hold up to ~2.14 billion, so (MOD + MOD) will not overflow.
        int[][][] dp = new int[zero + 1][one + 1][2];
        
        // Base cases: purely 0s or purely 1s up to the limit
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int j = 1; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }
        
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                
                // 1. Calculate combinations ending in '0'
                // Take all valid previous arrays and append '0'
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                
                // Subtract the exact number of combinations that exceeded the limit
                if (i > limit) {
                    dp[i][j][0] = (dp[i][j][0] - dp[i - 1 - limit][j][1] + MOD) % MOD;
                }
                
                // 2. Calculate combinations ending in '1'
                // Take all valid previous arrays and append '1'
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                
                // Subtract the exact number of combinations that exceeded the limit
                if (j > limit) {
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j - 1 - limit][0] + MOD) % MOD;
                }
            }
        }
        
        // The total is the sum of valid arrays ending in 0 and ending in 1
        return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }
}