import java.util.Arrays;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int maxPoints = 1;
        
        // We reuse this buffer to store packed slopes (dy << 32 | dx)
        long[] slopes = new long[n];

        for (int i = 0; i < n; i++) {
            // Pruning: If remaining points are fewer than current max, stop.
            if (n - i <= maxPoints) break;

            int p1x = points[i][0];
            int p1y = points[i][1];
            int slopeCount = 0;

            for (int j = i + 1; j < n; j++) {
                int p2x = points[j][0];
                int p2y = points[j][1];
                
                int dy = p2y - p1y;
                int dx = p2x - p1x;

                // Normalize the slope using GCD
                int gcd = gcd(Math.abs(dy), Math.abs(dx));
                dy /= gcd;
                dx /= gcd;

                // Standardize signs so identical lines have identical (dy, dx)
                // We usually enforce dx to be positive.
                if (dx < 0) {
                    dy = -dy;
                    dx = -dx;
                } else if (dx == 0) {
                    // Vertical line: ensure dy is always positive (1)
                    dy = Math.abs(dy);
                }

                // Pack dy and dx into a single long.
                // dy goes to upper 32 bits, dx stays in lower 32 bits.
                // We verify uniqueness without objects.
                slopes[slopeCount++] = ((long) dy << 32) | (dx & 0xFFFFFFFFL);
            }

            // If no points processed, continue
            if (slopeCount == 0) continue;

            // Sort the packed slopes
            Arrays.sort(slopes, 0, slopeCount);

            // Count streaks
            int currentStreak = 1;
            for (int k = 1; k < slopeCount; k++) {
                if (slopes[k] == slopes[k - 1]) {
                    currentStreak++;
                } else {
                    maxPoints = Math.max(maxPoints, currentStreak + 1);
                    currentStreak = 1;
                }
            }
            // Check the last streak
            maxPoints = Math.max(maxPoints, currentStreak + 1);
        }

        return maxPoints;
    }

    // Helper to find Greatest Common Divisor
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}