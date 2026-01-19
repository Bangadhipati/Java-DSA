class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // col0 tracks if the first column needs to be zeroed at the end
        int col0 = 1;

        // 1. Single pass to mark rows and columns using the first row/col
        for (int i = 0; i < m; i++) {
            // Check if the first column has a zero
            if (matrix[i][0] == 0) col0 = 0;
            
            // Start from the second column to avoid overwriting the col0 marker
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 2. Iterate backwards to fill zeroes
        // We go backwards so we don't overwrite our markers before using them
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // Finally, update the first column based on the col0 flag
            if (col0 == 0) matrix[i][0] = 0;
        }
    }
}