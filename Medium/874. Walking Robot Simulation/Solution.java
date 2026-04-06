import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Direction vectors: North, East, South, West
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0; // Robot starts facing North (index 0)
        
        int x = 0;
        int y = 0; // Fixed the typo here
        int maxDistSq = 0;
        
        // Encode obstacles into a HashSet of Longs for lightning-fast O(1) lookups
        Set<Long> obsSet = new HashSet<>(obstacles.length);
        for (int[] obs : obstacles) {
            obsSet.add(encode(obs[0], obs[1]));
        }
        
        for (int cmd : commands) {
            if (cmd == -1) {
                // Turn Right
                dir = (dir + 1) % 4;
            } else if (cmd == -2) {
                // Turn Left
                dir = (dir + 3) % 4;
            } else {
                // Move forward step-by-step
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dirs[dir][0];
                    int ny = y + dirs[dir][1];
                    
                    // If the next step is an obstacle, stop moving for this command
                    if (obsSet.contains(encode(nx, ny))) {
                        break;
                    }
                    
                    // Otherwise, complete the step
                    x = nx;
                    y = ny;
                    
                    // Track the maximum squared distance
                    maxDistSq = Math.max(maxDistSq, x * x + y * y);
                }
            }
        }
        
        return maxDistSq;
    }
    
    // Helper method to pack two 32-bit integers into a single 64-bit long
    private long encode(int x, int y) {
        // Shift x into the upper 32 bits. 
        // Use a bitwise AND mask on y to prevent negative sign extension into the upper bits.
        return (((long) x) << 32) | (y & 0xFFFFFFFFL);
    }
}