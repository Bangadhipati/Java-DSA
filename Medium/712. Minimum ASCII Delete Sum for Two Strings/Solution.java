class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // Memory Optimization: Ensure we use the shorter string for the DP array
        // This reduces space from O(N) to O(min(N, M))
        if (n > m) {
            return minimumDeleteSum(s2, s1);
        }
        
        // Convert to char arrays for faster access (avoids .charAt overhead)
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        
        // dp[j] stores the min delete sum for s1[0...i] and s2[0...j]
        int[] dp = new int[n + 1];
        
        // 1. Initialize Base Case (Row 0)
        // If s1 is empty, we must delete all characters in s2[0...j]
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j - 1] + c2[j - 1];
        }
        
        // 2. Iterate through s1 (Rows)
        for (int i = 1; i <= m; i++) {
            // 'diag' will store the value of dp[j-1] from the PREVIOUS row (i-1)
            // Initially, for j=0, it's dp[0] from the previous row.
            int diag = dp[0];
            
            // Update the first column (matching s1[0...i] against empty s2)
            // We just add the current s1 character to the deletion cost
            dp[0] += c1[i - 1];
            
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // Store old value (i-1, j) to become 'diag' for next step
                
                if (c1[i - 1] == c2[j - 1]) {
                    // Match found: No deletion cost added. Take value from diagonal.
                    dp[j] = diag;
                } else {
                    // Mismatch: Delete either char from s1 or char from s2
                    // dp[j] (before update) represents dp[i-1][j] (Delete s1 char)
                    // dp[j-1] (already updated) represents dp[i][j-1] (Delete s2 char)
                    
                    int deleteS1 = dp[j] + c1[i - 1];
                    int deleteS2 = dp[j - 1] + c2[j - 1];
                    
                    if (deleteS1 < deleteS2) {
                        dp[j] = deleteS1;
                    } else {
                        dp[j] = deleteS2;
                    }
                }
                
                // Update diag for the next column iteration
                diag = temp;
            }
        }
        
        return dp[n];
    }
}