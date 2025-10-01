class Solution {
    private char[] a, b;
    private Boolean[][][] memo;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) return false;
        a = s1.toCharArray();
        b = s2.toCharArray();
        memo = new Boolean[n][n][n + 1]; // startA, startB, length
        return dfs(0, 0, n);
    }

    private boolean dfs(int i, int j, int len) {
        if (memo[i][j][len] != null) return memo[i][j][len];
        
        // Direct match
        boolean same = true;
        for (int k = 0; k < len; k++) {
            if (a[i + k] != b[j + k]) {
                same = false;
                break;
            }
        }
        if (same) return memo[i][j][len] = true;

        // Prune with char count check
        int[] count = new int[26];
        for (int k = 0; k < len; k++) {
            count[a[i + k] - 'a']++;
            count[b[j + k] - 'a']--;
        }
        for (int c : count) if (c != 0) return memo[i][j][len] = false;

        // Try splitting
        for (int k = 1; k < len; k++) {
            // No swap
            if (dfs(i, j, k) && dfs(i + k, j + k, len - k)) 
                return memo[i][j][len] = true;
            // Swap
            if (dfs(i, j + len - k, k) && dfs(i + k, j, len - k)) 
                return memo[i][j][len] = true;
        }
        return memo[i][j][len] = false;
    }
}
