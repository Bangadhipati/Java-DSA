import java.util.Arrays;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        
        // 1. Calculate the consecutive height of 1s for each column
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }
        
        // 2. Evaluate the maximum area row by row
        for (int i = 0; i < m; i++) {
            // Clone the row so we don't ruin the column order for the next row's calculations
            int[] currRow = matrix[i].clone();
            
            // Sort in ascending order
            Arrays.sort(currRow);
            
            // Iterate from the back (tallest columns) to the front
            for (int j = n - 1; j >= 0; j--) {
                // Optimization: A height of 0 contributes 0 area, and since it's sorted, 
                // everything to the left is also 0. We can safely break.
                if (currRow[j] == 0) {
                    break; 
                }
                
                // The width is the number of columns we've included so far
                int width = n - j;
                int currentArea = currRow[j] * width;
                
                maxArea = Math.max(maxArea, currentArea);
            }
        }
        
        return maxArea;
    }
}