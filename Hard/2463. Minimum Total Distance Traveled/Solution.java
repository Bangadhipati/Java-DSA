import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // 1. Sort robots and factories left-to-right
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 2. Flatten the factories into individual 1-capacity slots
        List<Integer> factorySlots = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0];
            int limit = f[1];
            // We never need more slots than the total number of robots
            int usableSlots = Math.min(limit, robot.size());
            for (int i = 0; i < usableSlots; i++) {
                factorySlots.add(pos);
            }
        }
        
        int n = robot.size();
        int m = factorySlots.size();
        
        // dp[i][j] = min distance to repair robots i...n using slots j...m
        long[][] dp = new long[n + 1][m + 1];
        
        // Base Case: If we have robots left but run out of factory slots, it's impossible
        long INF = 100000000000000L; // Safely large number to avoid Long overflow
        for (int i = 0; i < n; i++) {
            dp[i][m] = INF;
        }
        
        // 3. Fill the DP table backward
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                
                // Choice 1: Skip this factory slot
                long skip = dp[i][j + 1];
                
                // Choice 2: Assign current robot to this factory slot
                long take = Math.abs((long) robot.get(i) - factorySlots.get(j)) + dp[i + 1][j + 1];
                
                // Record the optimal choice
                dp[i][j] = Math.min(skip, take);
            }
        }
        
        // The answer to the entire problem is sitting at the origin state
        return dp[0][0];
    }
}