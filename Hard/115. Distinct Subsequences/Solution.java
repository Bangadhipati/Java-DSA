class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) return 0;
        
        int[] dp = new int[n + 1];
        dp[0] = 1; // empty t can be formed in 1 way

        for (int i = 1; i <= m; i++) {
            // iterate backwards to avoid overwriting dp[j-1] before it's used
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n];
    }
}
