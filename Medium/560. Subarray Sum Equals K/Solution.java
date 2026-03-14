import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        // Map to store (Prefix Sum -> Frequency)
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        
        // Base case: A prefix sum of 0 happens exactly 1 time (before the array starts)
        prefixSumMap.put(0, 1);
        
        int count = 0;
        int currentSum = 0;
        
        for (int num : nums) {
            // 1. Update the running prefix sum
            currentSum += num;
            
            // 2. If (currentSum - k) exists in the map, we found valid subarrays!
            int target = currentSum - k;
            if (prefixSumMap.containsKey(target)) {
                count += prefixSumMap.get(target);
            }
            
            // 3. Add the current prefix sum to the map for future iterations
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}