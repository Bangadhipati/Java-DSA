class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] p = new int[n][m];
        
        long prefix = 1;
        
        // 1. Forward pass: Calculate the product of all elements BEFORE grid[i][j]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) prefix;
                // Update prefix for the next cell, keeping it modulo 12345
                prefix = (prefix * grid[i][j]) % 12345;
            }
        }
        
        long suffix = 1;
        
        // 2. Backward pass: Calculate the product of all elements AFTER grid[i][j]
        // and multiply it with the prefix product already stored in p[i][j]
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // Multiply the stored prefix by the current suffix
                p[i][j] = (int) ((p[i][j] * suffix) % 12345);
                // Update suffix for the next cell (moving backwards)
                suffix = (suffix * grid[i][j]) % 12345;
            }
        }
        
        return p;
    }
}