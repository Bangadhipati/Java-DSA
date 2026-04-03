import java.util.Arrays;

class Solution {
    // Renamed to match the platform's exact expected signature
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] robs = new int[n][2];
        for (int i = 0; i < n; i++) {
            robs[i][0] = robots[i];
            robs[i][1] = distance[i];
        }
        
        // 1. Sort robots left-to-right based on their physical position
        Arrays.sort(robs, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 2. Sort walls
        Arrays.sort(walls);
        
        // 3. Filter out walls that are directly sitting on top of a robot
        int[] validWalls = new int[walls.length];
        int validCount = 0;
        int baseWalls = 0;
        int rIdx = 0;
        
        for (int w : walls) {
            while (rIdx < n && robs[rIdx][0] < w) {
                rIdx++;
            }
            if (rIdx < n && robs[rIdx][0] == w) {
                baseWalls++; // Unconditionally destroyed
            } else {
                validWalls[validCount++] = w;
            }
        }
        
        int[] wArr = Arrays.copyOf(validWalls, validCount);
        
        // DP Table: dp[i][0] = Robot i shoots Left, dp[i][1] = Robot i shoots Right
        int[][] dp = new int[n][2];
        
        // Base Case for Robot 0
        dp[0][0] = countWalls(wArr, robs[0][0] - robs[0][1], robs[0][0] - 1);
        dp[0][1] = 0; // Shooting right doesn't destroy anything BEHIND Robot 0
        
        // 4. Traverse the segments between adjacent robots
        for (int i = 1; i < n; i++) {
            // Interval mapped by R_{i-1} shooting Right
            int A1 = robs[i-1][0] + 1;
            int B1 = Math.min(robs[i][0] - 1, robs[i-1][0] + robs[i-1][1]);
            boolean valid1 = A1 <= B1;
            
            // Interval mapped by R_i shooting Left
            int A2 = Math.max(robs[i-1][0] + 1, robs[i][0] - robs[i][1]);
            int B2 = robs[i][0] - 1;
            boolean valid2 = A2 <= B2;
            
            int LL = valid2 ? countWalls(wArr, A2, B2) : 0;
            int LR = 0;
            int RR = valid1 ? countWalls(wArr, A1, B1) : 0;
            
            int RL;
            // If both fire inwards and their bullet ranges overlap, merge the intervals
            if (valid1 && valid2 && B1 >= A2) {
                RL = countWalls(wArr, A1, Math.max(B1, B2));
            } else {
                RL = (valid1 ? countWalls(wArr, A1, B1) : 0) + (valid2 ? countWalls(wArr, A2, B2) : 0);
            }
            
            // DP Transitions
            dp[i][0] = Math.max(dp[i-1][0] + LL, dp[i-1][1] + RL);
            dp[i][1] = Math.max(dp[i-1][0] + LR, dp[i-1][1] + RR);
        }
        
        // 5. Account for the final segment after the last robot
        int lastRight = countWalls(wArr, robs[n-1][0] + 1, robs[n-1][0] + robs[n-1][1]);
        int maxUnique = Math.max(dp[n-1][0], dp[n-1][1] + lastRight);
        
        return maxUnique + baseWalls;
    }
    
    // Quick Binary Search helper to count walls strictly within [L, R]
    private int countWalls(int[] w, int L, int R) {
        if (L > R) return 0;
        int leftIdx = lowerBound(w, L);
        int rightIdx = upperBound(w, R) - 1;
        if (leftIdx <= rightIdx) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }
    
    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}