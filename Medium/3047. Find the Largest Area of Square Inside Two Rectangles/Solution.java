class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0;

        // Iterate through all pairs of rectangles
        for (int i = 0; i < n; i++) {
            // Optimization: Cache coordinates of rectangle i
            int x1_i = bottomLeft[i][0];
            int y1_i = bottomLeft[i][1];
            int x2_i = topRight[i][0];
            int y2_i = topRight[i][1];

            for (int j = i + 1; j < n; j++) {
                // Calculate intersection boundaries
                // Bottom-Left of intersection
                int interX1 = x1_i > bottomLeft[j][0] ? x1_i : bottomLeft[j][0];
                int interY1 = y1_i > bottomLeft[j][1] ? y1_i : bottomLeft[j][1];
                
                // Top-Right of intersection
                int interX2 = x2_i < topRight[j][0] ? x2_i : topRight[j][0];
                int interY2 = y2_i < topRight[j][1] ? y2_i : topRight[j][1];

                // Calculate width and height of the intersection
                int width = interX2 - interX1;
                int height = interY2 - interY1;

                // If they intersect
                if (width > 0 && height > 0) {
                    // Largest side of a square inside this intersection
                    int currentSide = width < height ? width : height;
                    
                    if (currentSide > maxSide) {
                        maxSide = currentSide;
                    }
                }
            }
        }

        return maxSide * maxSide;
    }
}