class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        
        // Optimize k to avoid redundant full-circle shifts
        k = k % n; 
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                if (i % 2 == 0) {
                    // Even row: Check if the element coming from the right matches
                    if (mat[i][j] != mat[i][(j + k) % n]) {
                        return false;
                    }
                } else {
                    // Odd row: Check if the element coming from the left matches
                    if (mat[i][j] != mat[i][(j - k + n) % n]) {
                        return false;
                    }
                }
                
            }
        }
        
        // If we checked every cell and found no mismatches, it's identical
        return true;
    }
}