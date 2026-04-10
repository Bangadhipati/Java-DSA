import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        // Group the indices of each number
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            indexMap.putIfAbsent(nums[i], new ArrayList<>());
            indexMap.get(nums[i]).add(i);
        }
        
        int minDistance = Integer.MAX_VALUE;
        
        // Check every group to find the tightest triplet
        for (List<Integer> indices : indexMap.values()) {
            if (indices.size() >= 3) {
                // We only need to check consecutive triplets (i, i+1, i+2)
                for (int i = 0; i < indices.size() - 2; i++) {
                    // The simplified math formula: 2 * (lastIndex - firstIndex)
                    int distance = 2 * (indices.get(i + 2) - indices.get(i));
                    minDistance = Math.min(minDistance, distance);
                }
            }
        }
        
        // If we never updated minDistance, no good tuple exists
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}