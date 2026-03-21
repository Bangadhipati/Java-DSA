class Solution {
    // Renamed to match the platform's exact expected signature
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // Iterate only halfway through the height of the submatrix
        for (int i = 0; i < k / 2; i++) {
            int topRow = x + i;
            int bottomRow = x + k - 1 - i;
            
            // Swap elements column by column within the k-width bound
            for (int j = y; j < y + k; j++) {
                int temp = grid[topRow][j];
                grid[topRow][j] = grid[bottomRow][j];
                grid[bottomRow][j] = temp;
            }
        }
        
        return grid;
    }
}