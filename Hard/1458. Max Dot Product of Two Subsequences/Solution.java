class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // Optimization: Ensure we allocate the smaller array to save memory
        // If nums2 is huge and nums1 is small, swap them so our DP array is small.
        if (m > n) {
            return maxDotProduct(nums2, nums1);
        }

        int[] dp = new int[m];
        
        // Step 1: Initialize the first row (base case) manually.
        // This corresponds to using only nums1[0] against all of nums2.
        dp[0] = nums1[0] * nums2[0];
        for (int j = 1; j < m; j++) {
            // Either take the current product, or carry over the max from the left.
            // This effectively finds the max single product involving nums1[0].
            dp[j] = Math.max(dp[j - 1], nums1[0] * nums2[j]);
        }

        // Step 2: Fill the rest of the table
        for (int i = 1; i < n; i++) {
            // 'prevDiag' holds the value of dp[j-1] from the PREVIOUS row (i-1)
            // It represents the best dot product before including nums1[i] and nums2[j].
            int prevDiag = dp[0]; 
            
            // Update the first column for the current row
            dp[0] = Math.max(dp[0], nums1[i] * nums2[0]);

            for (int j = 1; j < m; j++) {
                int temp = dp[j]; // Store old dp[j] (which acts as dp[i-1][j])
                int product = nums1[i] * nums2[j];
                
                // Calculate new dp[j] based on the 4 choices:
                
                // 1. product (Start fresh)
                // 2. prevDiag + product (Extend previous best)
                int currentMax = Math.max(product, prevDiag + product);
                
                // 3. dp[j] (Skip current nums1[i] - inherit from Top)
                currentMax = Math.max(currentMax, dp[j]);
                
                // 4. dp[j-1] (Skip current nums2[j] - inherit from Left)
                currentMax = Math.max(currentMax, dp[j - 1]);
                
                // Update state
                dp[j] = currentMax;
                prevDiag = temp; // The old dp[j] becomes the diagonal for the next column
            }
        }

        return dp[m - 1];
    }
}