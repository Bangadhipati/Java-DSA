import java.util.Arrays;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Flatten the 2D grid into a 1D array for easy sorting
        int[] flat = new int[m * n];
        int idx = 0;
        
        // Capture the required remainder based on the very first element
        int requiredMod = grid[0][0] % x;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                
                // If any element doesn't share the same remainder, it's impossible
                if (val % x != requiredMod) {
                    return -1;
                }
                
                flat[idx++] = val;
            }
        }
        
        // Sort the array to easily find the median
        Arrays.sort(flat);
        
        // The median minimizes the sum of absolute deviations
        int median = flat[flat.length / 2];
        
        int totalOperations = 0;
        for (int val : flat) {
            // The number of operations is simply the absolute difference divided by x
            totalOperations += Math.abs(val - median) / x;
        }
        
        return totalOperations;
    }
}