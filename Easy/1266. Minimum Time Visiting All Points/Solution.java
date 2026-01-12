class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int time = 0;
        
        // Cache the starting point coordinates
        // This avoids accessing points[i-1] inside the loop
        int prevX = points[0][0];
        int prevY = points[0][1];

        for (int i = 1; i < points.length; i++) {
            // Retrieve current point with a single array access
            // Storing 'curr' as a reference saves looking up 'points[i]' twice
            int[] curr = points[i];
            int currX = curr[0];
            int currY = curr[1];
            
            // Calculate absolute differences manually
            int dx = currX - prevX;
            int dy = currY - prevY;
            
            if (dx < 0) dx = -dx;
            if (dy < 0) dy = -dy;
            
            // Add the max difference to time
            if (dx > dy) {
                time += dx;
            } else {
                time += dy;
            }
            
            // Update 'prev' for the next iteration using the values we already have
            prevX = currX;
            prevY = currY;
        }
        
        return time;
    }
}