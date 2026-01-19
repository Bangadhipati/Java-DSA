class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Treat the 2D matrix as a sorted 1D array of length m * n
        int low = 0;
        int high = m * n - 1;
        
        while (low <= high) {
            // Standard binary search midpoint to avoid overflow
            int mid = low + (high - low) / 2;
            
            // Map the 1D index back to 2D coordinates
            int row = mid / n;
            int col = mid % n;
            
            int midValue = matrix[row][col];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return false;
    }
}