class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 1. Precompute Prefix Sums
        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[m + 1][n];
        int[][] d1 = new int[m + 1][n + 1]; // Main diagonal
        int[][] d2 = new int[m + 1][n + 1]; // Anti-diagonal

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j + 1] = rowSum[i][j] + grid[i][j];
                colSum[i + 1][j] = colSum[i][j] + grid[i][j];
                d1[i + 1][j + 1] = d1[i][j] + grid[i][j];
                d2[i + 1][j] = d2[i][j + 1] + grid[i][j];
            }
        }

        // 2. Search for the largest k starting from the maximum possible size
        for (int k = Math.min(m, n); k > 1; k--) {
            for (int i = 0; i <= m - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (isMagic(grid, i, j, k, rowSum, colSum, d1, d2)) {
                        return k;
                    }
                }
            }
        }

        return 1; // 1x1 is trivially magic
    }

    private boolean isMagic(int[][] grid, int r, int c, int k, 
                            int[][] rowSum, int[][] colSum, int[][] d1, int[][] d2) {
        // Calculate the target sum using the first row
        int target = rowSum[r][c + k] - rowSum[r][c];

        // Check all rows
        for (int i = 1; i < k; i++) {
            if (rowSum[r + i][c + k] - rowSum[r + i][c] != target) return false;
        }

        // Check all columns
        for (int j = 0; j < k; j++) {
            if (colSum[r + k][c + j] - colSum[r][c + j] != target) return false;
        }

        // Check main diagonal (top-left to bottom-right)
        if (d1[r + k][c + k] - d1[r][c] != target) return false;

        // Check anti-diagonal (top-right to bottom-left)
        // Adjusting indices for the d2 calculation
        if (d2[r + k][c] - d2[r][c + k] != target) return false;

        return true;
    }
}