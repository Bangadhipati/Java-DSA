class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        
        // Assume all 4 possible orientations are a match until proven otherwise
        boolean[] match = {true, true, true, true}; // 0, 90, 180, 270
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                // 0 Degrees check
                if (mat[i][j] != target[i][j]) {
                    match[0] = false;
                }
                
                // 90 Degrees clockwise check
                if (mat[i][j] != target[j][n - 1 - i]) {
                    match[1] = false;
                }
                
                // 180 Degrees check
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) {
                    match[2] = false;
                }
                
                // 270 Degrees clockwise check
                if (mat[i][j] != target[n - 1 - j][i]) {
                    match[3] = false;
                }
            }
        }
        
        // If even one orientation survived the checks, it's possible
        return match[0] || match[1] || match[2] || match[3];
    }
}