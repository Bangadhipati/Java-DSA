class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        long n = grid.length;
        long N = n * n;
        
        // 1. Calculate the mathematically expected sums
        long expectedSum = N * (N + 1) / 2;
        long expectedSqSum = N * (N + 1) * (2 * N + 1) / 6;
        
        long actualSum = 0;
        long actualSqSum = 0;
        
        // 2. Calculate the actual sums from the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                actualSum += val;
                actualSqSum += val * val;
            }
        }
        
        // 3. Set up the equations
        // diff1 = a - b
        long diff1 = actualSum - expectedSum; 
        
        // diff2 = a^2 - b^2 = (a - b)(a + b)
        long diff2 = actualSqSum - expectedSqSum; 
        
        // sumPlus = a + b = (a^2 - b^2) / (a - b)
        long sumPlus = diff2 / diff1; 
        
        // 4. Solve for a and b
        // a = ((a - b) + (a + b)) / 2
        int a = (int) ((diff1 + sumPlus) / 2);
        
        // b = (a + b) - a
        int b = (int) (sumPlus - a);
        
        return new int[]{a, b};
    }
}