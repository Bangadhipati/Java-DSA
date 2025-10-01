class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        int[] dp = new int[n]; // dp[i] = min cuts for s[0..i]

        // Precompute palindrome substrings
        for (int end = 0; end < n; end++) {
            dp[end] = end; // max cuts = length - 1
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) && 
                   (end - start <= 2 || isPal[start + 1][end - 1])) {
                    isPal[start][end] = true;
                    if (start == 0) {
                        dp[end] = 0; // whole substring is palindrome
                    } else {
                        dp[end] = Math.min(dp[end], dp[start - 1] + 1);
                    }
                }
            }
        }

        return dp[n - 1];
    }
}
