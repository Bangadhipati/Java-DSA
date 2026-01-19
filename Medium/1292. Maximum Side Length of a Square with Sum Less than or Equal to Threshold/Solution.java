class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        
        // 1. Build 2D Prefix Sum Matrix
        // P[i][j] is the sum of mat from (0,0) to (i-1, j-1)
        int[][] P = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                P[i][j] = mat[i - 1][j - 1] + P[i - 1][j] + P[i][j - 1] - P[i - 1][j - 1];
            }
        }
        
        int maxSide = 0;
        
        // 2. Iterate through the matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // We only care if we can find a square bigger than the current maxSide
                int nextSide = maxSide + 1;
                
                // Check if a square of size 'nextSide' ending at (i, j) is possible
                if (i >= nextSide && j >= nextSide) {
                    // Calculate sum of square of side 'nextSide' using 2D Prefix Sum
                    int currentSum = P[i][j] - P[i - nextSide][j] - P[i][j - nextSide] + P[i - nextSide][j - nextSide];
                    
                    if (currentSum <= threshold) {
                        maxSide = nextSide;
                    }
                }
            }
        }
        
        return maxSide;
    }
}