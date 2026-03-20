import java.util.Arrays;

class Solution {
    // Renamed to match the platform's exact expected signature
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        
        // Pre-allocate the window array to avoid memory overhead during loops
        int[] window = new int[k * k];
        
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                
                // 1. Extract the k x k submatrix into the 1D array
                int idx = 0;
                for (int r = i; r < i + k; r++) {
                    for (int c = j; c < j + k; c++) {
                        window[idx++] = grid[r][c];
                    }
                }
                
                // 2. Sort the array
                Arrays.sort(window);
                
                // 3. Find the minimum absolute difference between distinct elements
                int minDiff = Integer.MAX_VALUE;
                for (int w = 1; w < window.length; w++) {
                    if (window[w] != window[w - 1]) {
                        minDiff = Math.min(minDiff, window[w] - window[w - 1]);
                    }
                }
                
                // 4. Handle the edge cases (all elements identical, or k = 1)
                if (minDiff == Integer.MAX_VALUE) {
                    ans[i][j] = 0;
                } else {
                    ans[i][j] = minDiff;
                }
            }
        }
        
        return ans;
    }
}