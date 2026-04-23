import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n]; // Return array must be long to prevent overflow
        
        // Map to group indices by their corresponding value in nums
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        // Process each group of identical numbers independently
        for (List<Integer> indices : indexMap.values()) {
            int k = indices.size();
            
            // Calculate total sum of all indices in this group
            long totalSum = 0;
            for (int idx : indices) {
                totalSum += idx;
            }
            
            long leftSum = 0;
            
            // Sweep through the indices to calculate distances using prefix math
            for (int i = 0; i < k; i++) {
                long currentIdx = indices.get(i);
                
                // Right sum is the total minus what we've passed (leftSum) and the current item
                long rightSum = totalSum - leftSum - currentIdx;
                
                // Elements to the left are smaller, so it's (Count * Value) - Sum
                long leftDistance = (long) i * currentIdx - leftSum;
                
                // Elements to the right are larger, so it's Sum - (Count * Value)
                long rightDistance = rightSum - (long) (k - 1 - i) * currentIdx;
                
                // Total distance is the sum of both sides
                arr[(int) currentIdx] = leftDistance + rightDistance;
                
                // Add the current index to the left sum for the next iteration
                leftSum += currentIdx;
            }
        }
        
        return arr;
    }
}