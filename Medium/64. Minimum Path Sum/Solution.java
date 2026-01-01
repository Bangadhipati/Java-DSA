class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Loop through every cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                
                // Case 1: The Start Position (Top-Left)
                // Do nothing. The cost is just the value itself.
                if (i == 0 && j == 0) {
                    continue;
                }
                
                // Case 2: Top Row (Can only come from the Left)
                else if (i == 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                }
                
                // Case 3: Left Column (Can only come from Above)
                else if (j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                }
                
                // Case 4: Middle Cells (Pick the min of Top or Left)
                else {
                    // grid[i-1][j] is Top
                    // grid[i][j-1] is Left
                    // Math.min picks the smaller one
                    grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        
        // Return the value accumulated in the bottom-right corner
        return grid[rows - 1][cols - 1];
    }
}