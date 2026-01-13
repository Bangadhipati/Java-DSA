class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double minStart = 2e9; // Initialize with a value larger than max constraint (10^9)
        double maxEnd = 0;

        // 1. Calculate Total Area and the Range [minStart, maxEnd] for Binary Search
        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            // Use double for calculation to preserve precision
            totalArea += l * l;
            
            if (y < minStart) minStart = y;
            if (y + l > maxEnd) maxEnd = y + l;
        }

        double target = totalArea / 2.0;
        double low = minStart;
        double high = maxEnd;

        // 2. Binary Search
        // Running for a fixed number of iterations (60) is a standard technique 
        // for floating point binary search to avoid infinite loops and ensure high precision.
        // Range 10^9 / 2^60 is approximately 10^-9 precision, well within 10^-5 requirement.
        for (int i = 0; i < 60; i++) {
            double mid = low + (high - low) / 2;
            
            // If we have enough area below, try to find a smaller Y (move high down)
            if (getAreaBelow(squares, mid) >= target) {
                high = mid;
            } else {
                // Not enough area, we must go higher
                low = mid;
            }
        }
        
        return high;
    }

    // Helper function to calculate total area of squares below line Y
    private double getAreaBelow(int[][] squares, double y) {
        double area = 0;
        for (int[] sq : squares) {
            double bottom = sq[1];
            double len = sq[2];
            double top = bottom + len;

            if (y >= top) {
                // Case 1: The line is above the square (Full square is below)
                area += len * len;
            } else if (y > bottom) {
                // Case 2: The line cuts through the square
                // Height below line is (y - bottom)
                area += (y - bottom) * len;
            }
            // Case 3: If y <= bottom, square is completely above, contributes 0.
        }
        return area;
    }
}