import java.util.Arrays;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // Step 1: Add boundaries and sort primitive arrays
        int[] h = prepareFences(hFences, m);
        int[] v = prepareFences(vFences, n);
        
        // Step 2: Generate all possible horizontal distances
        int[] hDist = new int[(h.length * (h.length - 1)) / 2];
        int hIdx = 0;
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                hDist[hIdx++] = h[j] - h[i];
            }
        }
        // Sorting distances allows for O(log N) lookup
        Arrays.sort(hDist);

        long maxSide = -1;

        // Step 3: Check vertical distances from largest to smallest
        // This allows us to exit early once a distance is smaller than maxSide
        for (int i = v.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                int dist = v[i] - v[j];
                
                // If this distance is already smaller than our best, 
                // all other 'j' for this 'i' will also be smaller.
                if (dist <= maxSide) break; 
                
                // Manual binary search is often faster than Arrays.binarySearch
                if (exists(hDist, dist)) {
                    maxSide = dist;
                }
            }
        }

        if (maxSide == -1) return -1;

        // Step 4: Final calculation with modulo
        return (int) ((maxSide * maxSide) % 1000000007);
    }

    private int[] prepareFences(int[] fences, int boundary) {
        int[] res = new int[fences.length + 2];
        for (int i = 0; i < fences.length; i++) res[i] = fences[i];
        res[fences.length] = 1;
        res[fences.length + 1] = boundary;
        Arrays.sort(res);
        return res;
    }

    private boolean exists(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] == target) return true;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
}