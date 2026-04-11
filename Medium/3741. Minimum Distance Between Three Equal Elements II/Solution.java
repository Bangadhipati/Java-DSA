import java.util.Arrays;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        // Since nums[i] <= n, we can use arrays instead of HashMaps for O(1) access
        int[] lastPos = new int[n + 1];
        int[] secondLastPos = new int[n + 1];
        
        // Initialize with -1 to indicate the number hasn't been seen yet
        Arrays.fill(lastPos, -1);
        Arrays.fill(secondLastPos, -1);
        
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            // If we have seen this value at least twice before, we have a triplet
            if (secondLastPos[val] != -1) {
                // Formula: 2 * (current_index - index_from_two_steps_ago)
                int currentDist = 2 * (i - secondLastPos[val]);
                if (currentDist < minDistance) {
                    minDistance = currentDist;
                }
            }
            
            // Shift the history: current becomes last, last becomes second-last
            secondLastPos[val] = lastPos[val];
            lastPos[val] = i;
        }
        
        return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
    }
}