class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        // Constraint is 10^5, so 100001 is sufficient "infinity".
        // Avoids loading the Integer class (Integer.MAX_VALUE).
        int min = 100001; 
        
        // Track odd/even count with a simple boolean to save stack space
        boolean oddNeg = false; 

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int val = matrix[i][j];

                // Manual absolute value without Math.abs()
                if (val < 0) {
                    val = -val;
                    // Toggle the boolean (true -> false -> true)
                    oddNeg = !oddNeg;
                }

                sum += val;

                // Manual min check without Math.min()
                if (val < min) {
                    min = val;
                }
            }
        }

        // If we have an odd number of negatives, subtract 2 * smallest absolute value
        if (oddNeg) {
            sum -= 2L * min;
        }

        return sum;
    }
}