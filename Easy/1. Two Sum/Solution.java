import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Map to store number -> index
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Traverse the array
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // The number we need to find
            
            // If complement is already in the map, we found the pair
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            
            // Otherwise, store the current number with its index
            map.put(nums[i], i);
        }
        
        // Problem guarantees one solution, so this won't be reached
        throw new IllegalArgumentException("No two sum solution");
    }
}
