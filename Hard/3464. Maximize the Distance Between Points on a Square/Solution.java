import java.util.Arrays;

class Solution {
    // Renamed to strictly match the hidden driver's expected signature
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] arr = new long[n];
        
        // Step 1: Unwrap the 2D square into a 1D perimeter line
        for (int i = 0; i < n; i++) {
            arr[i] = mapTo1D(points[i][0], points[i][1], side);
        }
        
        // Sort the points by their position on the perimeter
        Arrays.sort(arr);
        
        long limit = 4L * side; // Total circumference of the square
        
        // Step 2: Binary search the maximum possible minimum distance
        long low = 0;
        long high = side; 
        long maxDist = 0;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (isValid(mid, arr, k, limit)) {
                maxDist = mid;
                low = mid + 1; // Try to find a larger minimum distance
            } else {
                high = mid - 1; // Distance is too large, reduce it
            }
        }
        
        return (int) maxDist;
    }
    
    // Helper to map 2D coordinates to 1D perimeter position
    private long mapTo1D(int x, int y, int side) {
        if (y == 0) return x;                           // Bottom edge
        if (x == side) return (long) side + y;          // Right edge
        if (y == side) return 3L * side - x;            // Top edge
        if (x == 0) return 4L * side - y;               // Left edge
        return 0;
    }
    
    // Greedy checker to see if distance D is achievable for k points
    private boolean isValid(long D, long[] arr, int k, long limit) {
        int n = arr.length;
        
        // Duplicate array to cleanly simulate circular wrapping
        long[] arr2 = new long[n * 2];
        for (int i = 0; i < n; i++) {
            arr2[i] = arr[i];
            arr2[i + n] = arr[i] + limit;
        }
        
        // Precompute the index of the next valid point that is at least D distance away
        int[] nxt = new int[n * 2];
        int j = 0;
        for (int i = 0; i < 2 * n; i++) {
            while (j < 2 * n && arr2[j] - arr2[i] < D) {
                j++;
            }
            nxt[i] = j;
        }
        
        // Optimization: We only need to check starting positions up to the first valid jump.
        // If a valid k-sequence exists, one must start within this initial window.
        int endCheck = Math.min(n, nxt[0]);
        
        for (int i = 0; i <= endCheck; i++) {
            if (i == n) break; // Don't start checking from the wrapped portion
            
            int curr = i;
            int count = 1;
            
            // Greedily chain k-1 jumps
            while (count < k && curr < 2 * n) {
                curr = nxt[curr];
                count++;
            }
            
            // Check if we successfully placed k points AND the gap wrapping back to the start is >= D
            if (curr < 2 * n && (arr2[i] + limit) - arr2[curr] >= D) {
                return true;
            }
        }
        
        return false;
    }
}