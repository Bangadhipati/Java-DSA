import java.util.Arrays;

class Solution {
    public int minPairSum(int[] nums) {
        // 1. Sort the array to easily pick smallest and largest elements
        Arrays.sort(nums);
        
        int n = nums.length;
        int maxPairSum = 0;
        
        // 2. Use two pointers (effectively) to pair smallest with largest
        // We only need to go up to n/2 because we are checking pairs
        for (int i = 0; i < n / 2; i++) {
            int currentPairSum = nums[i] + nums[n - 1 - i];
            
            // 3. Update the maximum pair sum found so far
            if (currentPairSum > maxPairSum) {
                maxPairSum = currentPairSum;
            }
        }
        
        return maxPairSum;
    }
}