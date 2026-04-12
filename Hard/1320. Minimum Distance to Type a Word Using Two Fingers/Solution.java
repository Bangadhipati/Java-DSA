class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        if (n <= 2) return 0; // Two fingers can type any two letters with 0 distance
        
        // save[a] tracks the max distance saved if the "other" finger is on char 'a'
        // Index 26 is used for the initial "hovering" state of a finger
        int[] save = new int[27];
        int totalDist = 0;
        
        for (int i = 0; i < n - 1; i++) {
            int b = word.charAt(i) - 'A';
            int c = word.charAt(i + 1) - 'A';
            
            // The cost if we were forced to use only 1 finger
            int d = dist(b, c);
            totalDist += d;
            
            // Calculate the maximum saving we can get if we SWITCH fingers, 
            // making 'b' the new resting place for our inactive finger.
            int maxSaveForB = 0;
            for (int a = 0; a < 27; a++) {
                maxSaveForB = Math.max(maxSaveForB, save[a] + d - dist(a, c));
            }
            
            // Update the state
            save[b] = Math.max(save[b], maxSaveForB);
        }
        
        // Find the absolute highest saving we achieved across all possible finger positions
        int maxSave = 0;
        for (int s : save) {
            maxSave = Math.max(maxSave, s);
        }
        
        // The minimum actual distance is the 1-finger distance minus our best savings
        return totalDist - maxSave;
    }
    
    // Helper function to calculate coordinate distance
    private int dist(int a, int b) {
        if (a == 26) return 0; // A hovering finger costs 0 distance to place anywhere
        
        // Convert the 1D character index (0-25) into a 2D (row, col) coordinate on a 6-column grid
        int r1 = a / 6, c1 = a % 6;
        int r2 = b / 6, c2 = b % 6;
        
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}