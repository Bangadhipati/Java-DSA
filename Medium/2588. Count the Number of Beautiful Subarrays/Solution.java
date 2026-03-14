import java.util.HashMap;
import java.util.Map;

class Solution {
    public long beautifulSubarrays(int[] nums) {
        // Map to store (Prefix XOR -> Frequency)
        Map<Integer, Integer> prefixXorMap = new HashMap<>();
        
        // Base case: A prefix XOR of 0 happens exactly 1 time (before the array starts)
        prefixXorMap.put(0, 1);
        
        long count = 0;
        int currentXor = 0;
        
        for (int num : nums) {
            // 1. Update the running prefix XOR
            currentXor ^= num;
            
            // 2. If we've seen this exact XOR state before, the numbers strictly 
            // between then and now must XOR to exactly 0.
            if (prefixXorMap.containsKey(currentXor)) {
                count += prefixXorMap.get(currentXor);
            }
            
            // 3. Add the current prefix XOR state to the map for future iterations
            prefixXorMap.put(currentXor, prefixXorMap.getOrDefault(currentXor, 0) + 1);
        }
        
        return count;
    }
}