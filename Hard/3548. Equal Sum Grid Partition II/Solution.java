class Solution {
    // Renamed to match the platform's exact expected signature
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        long totalSum = 0;
        int[] fullCount = new int[100005];
        
        // 1. Initial full grid sum and frequencies
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
                fullCount[grid[i][j]]++;
            }
        }
        
        // ==========================================
        // PASS 1: HORIZONTAL CUTS
        // ==========================================
        int[] c1 = new int[100005];
        int[] c2 = fullCount.clone();
        long S1 = 0;
        
        for (int i = 0; i < m - 1; i++) {
            // Shift the current row from Section 2 to Section 1
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                c1[val]++;
                c2[val]--;
                S1 += val;
            }
            
            long S2 = totalSum - S1;
            long D = S1 - S2;
            
            // Perfect match without discounts
            if (D == 0) return true;
            
            // Section 1 is larger: We must find a cell equal to D in Section 1
            if (D > 0 && D <= 100000) {
                boolean valid = false;
                if (i == 0) { // Section 1 is a 1D horizontal strip
                    valid = (grid[0][0] == D || grid[0][n - 1] == D);
                } else if (n == 1) { // Section 1 is a 1D vertical strip
                    valid = (grid[0][0] == D || grid[i][0] == D);
                } else { // Section 1 is at least 2x2
                    valid = (c1[(int) D] > 0);
                }
                if (valid) return true;
            } 
            // Section 2 is larger: We must find a cell equal to -D in Section 2
            else if (D < 0 && -D <= 100000) {
                long target = -D;
                boolean valid = false;
                if (i == m - 2) { // Section 2 is a 1D horizontal strip
                    valid = (grid[m - 1][0] == target || grid[m - 1][n - 1] == target);
                } else if (n == 1) { // Section 2 is a 1D vertical strip
                    valid = (grid[i + 1][0] == target || grid[m - 1][0] == target);
                } else { // Section 2 is at least 2x2
                    valid = (c2[(int) target] > 0);
                }
                if (valid) return true;
            }
        }
        
        // ==========================================
        // PASS 2: VERTICAL CUTS
        // ==========================================
        c1 = new int[100005];
        c2 = fullCount.clone();
        S1 = 0;
        
        for (int j = 0; j < n - 1; j++) {
            // Shift the current column from Section 2 to Section 1
            for (int i = 0; i < m; i++) {
                int val = grid[i][j];
                c1[val]++;
                c2[val]--;
                S1 += val;
            }
            
            long S2 = totalSum - S1;
            long D = S1 - S2;
            
            if (D == 0) return true;
            
            if (D > 0 && D <= 100000) {
                boolean valid = false;
                if (j == 0) { // Section 1 is a 1D vertical strip
                    valid = (grid[0][0] == D || grid[m - 1][0] == D);
                } else if (m == 1) { // Section 1 is a 1D horizontal strip
                    valid = (grid[0][0] == D || grid[0][j] == D);
                } else { // Section 1 is at least 2x2
                    valid = (c1[(int) D] > 0);
                }
                if (valid) return true;
            } else if (D < 0 && -D <= 100000) {
                long target = -D;
                boolean valid = false;
                if (j == n - 2) { // Section 2 is a 1D vertical strip
                    valid = (grid[0][n - 1] == target || grid[m - 1][n - 1] == target);
                } else if (m == 1) { // Section 2 is a 1D horizontal strip
                    valid = (grid[0][j + 1] == target || grid[0][n - 1] == target);
                } else { // Section 2 is at least 2x2
                    valid = (c2[(int) target] > 0);
                }
                if (valid) return true;
            }
        }
        
        return false;
    }
}