class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 1D arrays to keep track of the counts in each column up to the current row
        int[] colX = new int[n];
        int[] colY = new int[n];
        
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            int currentX = 0;
            int currentY = 0;
            
            for (int j = 0; j < n; j++) {
                // 1. Update the column running totals for the current cell
                if (grid[i][j] == 'X') {
                    colX[j]++;
                } else if (grid[i][j] == 'Y') {
                    colY[j]++;
                }
                
                // 2. Accumulate the column totals into the 2D submatrix totals
                currentX += colX[j];
                currentY += colY[j];
                
                // 3. Check the problem's exact conditions
                if (currentX > 0 && currentX == currentY) {
                    count++;
                }
            }
        }
        
        return count;
    }
}