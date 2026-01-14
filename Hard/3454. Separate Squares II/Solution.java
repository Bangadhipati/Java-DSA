import java.util.Arrays;

class Solution {
    // Arrays for Segment Tree
    // count: how many active squares cover this node's range fully
    // length: the length of union of segments active in this node's range
    int[] count;
    double[] length;
    
    // The unique sorted X coordinates
    double[] uniqueX;

    public double separateSquares(int[][] squares) {
        int n = squares.length;
        
        // 1. Coordinate Compression for X-axis
        // We collect all x and x+l to define the vertical strips
        double[] xCoords = new double[2 * n];
        for (int i = 0; i < n; i++) {
            xCoords[2 * i] = squares[i][0];
            xCoords[2 * i + 1] = (double) squares[i][0] + squares[i][2];
        }
        
        // Sort and remove duplicates
        Arrays.sort(xCoords);
        
        // Count unique coordinates
        int uniqueCount = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (i == 0 || xCoords[i] != xCoords[i - 1]) {
                uniqueCount++;
            }
        }
        
        // Create the compact uniqueX array
        uniqueX = new double[uniqueCount];
        int idx = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (i == 0 || xCoords[i] != xCoords[i - 1]) {
                uniqueX[idx++] = xCoords[i];
            }
        }
        
        // 2. Prepare Sweep Events (Y-axis)
        // Each square creates a start (+1) and end (-1) event
        Event[] events = new Event[2 * n];
        for (int i = 0; i < n; i++) {
            events[2 * i] = new Event(squares[i][1], 1, squares[i][0], (double)squares[i][0] + squares[i][2]);
            events[2 * i + 1] = new Event((double)squares[i][1] + squares[i][2], -1, squares[i][0], (double)squares[i][0] + squares[i][2]);
        }
        
        // Sort events by Y
        Arrays.sort(events, (a, b) -> Double.compare(a.y, b.y));
        
        // 3. Initialize Segment Tree
        // The tree needs to cover the ranges between unique X coordinates
        // If there are M unique X's, there are M-1 intervals. Tree size roughly 4*M.
        int m = uniqueX.length;
        count = new int[4 * m];
        length = new double[4 * m];
        
        double totalArea = 0;
        
        // We will store the history of the sweep to find the midpoint later
        // Because re-running the sweep is expensive, we capture the data:
        // {y_start, height_of_strip, effective_width_of_strip}
        double[][] strips = new double[2 * n][3];
        int stripCount = 0;

        // 4. Run Plane Sweep
        for (int i = 0; i < events.length; i++) {
            Event e = events[i];
            
            // Map the square's X-range to indices in our uniqueX array
            // Optimization: Binary Search for index (Arrays.binarySearch)
            int leftIdx = Arrays.binarySearch(uniqueX, e.x1);
            int rightIdx = Arrays.binarySearch(uniqueX, e.x2);
            
            // Update Segment Tree
            // Update range [leftIdx, rightIdx - 1] because the segment tree leaves represent intervals
            if (leftIdx < rightIdx) {
                update(1, 0, m - 1, leftIdx, rightIdx - 1, e.type);
            }
            
            // If this is not the last event, calculate area added by the next strip
            if (i + 1 < events.length) {
                double nextY = events[i + 1].y;
                double h = nextY - e.y;
                
                if (h > 0) {
                    double currentUnionWidth = length[1]; // Root holds total active width
                    double areaChunk = currentUnionWidth * h;
                    
                    totalArea += areaChunk;
                    
                    // Store strip info: y, h, width
                    strips[stripCount][0] = e.y;
                    strips[stripCount][1] = h;
                    strips[stripCount][2] = currentUnionWidth;
                    stripCount++;
                }
            }
        }
        
        // 5. Find the precise Y for half area
        double target = totalArea / 2.0;
        double currentArea = 0;
        
        for (int i = 0; i < stripCount; i++) {
            double y = strips[i][0];
            double h = strips[i][1];
            double w = strips[i][2];
            
            double areaChunk = w * h;
            
            if (currentArea + areaChunk >= target) {
                // The split point is inside this strip
                double needed = target - currentArea;
                // Area = width * height_needed -> height_needed = Area / width
                return y + (needed / w);
            }
            
            currentArea += areaChunk;
        }
        
        return events[events.length - 1].y; // Fallback (should not reach here)
    }
    
    // Segment Tree Update
    // node: current tree node index
    // start, end: range of uniqueX indices this node covers
    // l, r: range of uniqueX indices we want to update
    // val: +1 (add square) or -1 (remove square)
    private void update(int node, int start, int end, int l, int r, int val) {
        if (l > end || r < start) return;
        
        if (l <= start && end <= r) {
            count[node] += val;
        } else {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, l, r, val);
            update(node * 2 + 1, mid + 1, end, l, r, val);
        }
        
        // Update 'length' for this node
        if (count[node] > 0) {
            // If this node is fully covered by at least one square, 
            // its length is the full physical distance it represents.
            length[node] = uniqueX[end + 1] - uniqueX[start];
        } else {
            // If count is 0, it relies on children (unless it's a leaf)
            if (start != end) {
                length[node] = length[node * 2] + length[node * 2 + 1];
            } else {
                length[node] = 0;
            }
        }
    }
    
    // Simple Event class
    static class Event {
        double y;
        int type;
        double x1, x2;
        
        Event(double y, int type, double x1, double x2) {
            this.y = y;
            this.type = type;
            this.x1 = x1;
            this.x2 = x2;
        }
    }
}