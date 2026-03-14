import java.util.Arrays;

class Solution {
    public int numberOfPermutations(int n, int[][] requirements) {
        int MOD = 1_000_000_007;
        
        // Convert requirements to a fast-lookup array. 
        // req[i] stores the required inversions at prefix length i+1.
        int[] req = new int[n];
        Arrays.fill(req, -1);
        for (int[] r : requirements) {
            req[r[0]] = r[1];
        }
        
        // If the first element requires more than 0 inversions, it's mathematically impossible
        if (req[0] > 0) return 0;
        
        // dp[i][j] represents permutations of length i+1 having exactly j inversions
        int[][] dp = new int[n][401];
        
        // Base case: 1 way to arrange a single element with 0 inversions
        dp[0][0] = 1;
        
        // Iterate through each length from 1 to n-1
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 400; j++) {
                
                // If there's a strict requirement for this prefix length, 
                // and our current 'j' doesn't match it, skip it (stays 0).
                if (req[i] != -1 && req[i] != j) {
                    continue;
                }
                
                // Calculate the sum of valid states from the previous length
                long sum = 0;
                for (int k = 0; k <= i; k++) {
                    if (j - k >= 0) {
                        sum = (sum + dp[i - 1][j - k]) % MOD;
                    }
                }
                dp[i][j] = (int) sum;
            }
        }
        
        // Return the count of valid permutations of length n that meet the final requirement
        return dp[n - 1][req[n - 1]];
    }
}