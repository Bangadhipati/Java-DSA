class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        // We only need to track the current row's liquid levels.
        // Size 101 covers the maximum row width (index 0 to 99 + overflow).
        double[] currentRow = new double[101];
        
        // Pour everything into the top glass initially
        currentRow[0] = poured;

        // Iterate through each row up to the target query_row
        for (int row = 0; row < query_row; row++) {
            double[] nextRow = new double[101];
            
            // Process each glass in the current row
            // We only need to check up to 'row' because that's how wide the pyramid is
            for (int glass = 0; glass <= row; glass++) {
                
                // If this glass received more than 1 cup, it overflows
                double liquid = currentRow[glass];
                if (liquid > 1) {
                    double overflow = (liquid - 1) / 2.0;
                    nextRow[glass] += overflow;      // Flow to left child
                    nextRow[glass + 1] += overflow;  // Flow to right child
                }
            }
            // Move to the next level
            currentRow = nextRow;
        }

        // The glass can hold at most 1 cup. If it received more, it's just full (1.0).
        return Math.min(1.0, currentRow[query_glass]);
    }
}