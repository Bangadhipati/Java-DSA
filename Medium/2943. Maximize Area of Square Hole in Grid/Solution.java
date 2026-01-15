import java.util.Arrays;

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        // Calculate the maximum continuous gap for horizontal and vertical cuts
        int gapH = getMaxGap(hBars);
        int gapV = getMaxGap(vBars);
        
        // The largest square is limited by the shorter dimension
        int side = Math.min(gapH, gapV);
        
        return side * side;
    }
    
    // Helper function to find the longest sequence of consecutive bars
    private int getMaxGap(int[] bars) {
        Arrays.sort(bars);
        
        int maxStreak = 1;
        int currentStreak = 1;
        
        for (int i = 1; i < bars.length; i++) {
            // Check if the current bar is consecutive to the previous one
            if (bars[i] == bars[i - 1] + 1) {
                currentStreak++;
            } else {
                // Streak broken, reset
                currentStreak = 1;
            }
            
            // Update max found so far
            if (currentStreak > maxStreak) {
                maxStreak = currentStreak;
            }
        }
        
        // A streak of 'k' bars creates a hole of size 'k + 1'
        return maxStreak + 1;
    }
}