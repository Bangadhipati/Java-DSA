class Solution {
    public int minDistance(String word1, String word2) {
        // Optimization 1: Convert to char arrays for faster access
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        
        int m = s1.length;
        int n = s2.length;
        
        // Optimization 2: Space efficiency. 
        // We want the inner loop (and thus the DP array) to be the shorter string.
        // This ensures space complexity is O(min(m, n)).
        if (n > m) {
            return minDistance(word2, word1);
        }

        // We only need one 1D array of size 'n' (the shorter string)
        int[] dp = new int[n + 1];

        // 1. Initialize Base Case (Row 0)
        // Converting "" to word2 requires inserting j characters.
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        // 2. Iterate through the rows (s1)
        for (int i = 1; i <= m; i++) {
            // 'prev' holds the value of the "diagonal" (top-left neighbor).
            // At the start of a row, dp[0] was 'i-1'.
            int prev = dp[0]; 
            
            // The first element of the new row represents converting s1[0...i] to ""
            // This always costs 'i' deletions.
            dp[0] = i; 

            for (int j = 1; j <= n; j++) {
                // Store the value of dp[j] BEFORE we overwrite it.
                // This value acts as the "top" neighbor for the current cell,
                // but will become the "diagonal" (prev) for the NEXT cell in the loop.
                int temp = dp[j]; 
                
                if (s1[i - 1] == s2[j - 1]) {
                    // If characters match, the cost is the same as the diagonal (prev)
                    dp[j] = prev;
                } else {
                    // If mismatch, take 1 + min(Replace, Delete, Insert)
                    // prev      = Replace (diagonal)
                    // temp      = Delete  (top, which is the old dp[j])
                    // dp[j-1]   = Insert  (left, which is the new/updated value)
                    
                    // Unrolling Math.min for speed (slightly faster than nested calls)
                    int min = prev < temp ? prev : temp;
                    if (dp[j - 1] < min) min = dp[j - 1];
                    
                    dp[j] = 1 + min;
                }
                
                // Update prev for the next iteration
                prev = temp;
            }
        }
        
        return dp[n];
    }
}