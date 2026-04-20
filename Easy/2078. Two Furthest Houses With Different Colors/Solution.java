class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        
        // Scenario 1: Anchor on the first house. 
        // Scan from right to left to find the first different color.
        int distFromStart = 0;
        for (int i = n - 1; i > 0; i--) {
            if (colors[i] != colors[0]) {
                distFromStart = i; // Distance from 0 to i is just i
                break;
            }
        }
        
        // Scenario 2: Anchor on the last house. 
        // Scan from left to right to find the first different color.
        int distFromEnd = 0;
        for (int i = 0; i < n - 1; i++) {
            if (colors[i] != colors[n - 1]) {
                distFromEnd = (n - 1) - i;
                break;
            }
        }
        
        // Return the absolute maximum of the two scenarios
        return Math.max(distFromStart, distFromEnd);
    }
}