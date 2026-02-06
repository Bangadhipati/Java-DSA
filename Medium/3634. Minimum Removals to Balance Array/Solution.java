import java.util.Arrays;

class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) return 0;

        // 1. Sort the array so that for any window [left, right],
        // nums[left] is the minimum and nums[right] is the maximum.
        Arrays.sort(nums);

        int maxKept = 0;
        int left = 0;

        // 2. Sliding window to find the widest range where max <= min * k
        for (int right = 0; right < n; right++) {
            // Condition check: Use long to prevent overflow during multiplication
            while ((long) nums[right] > (long) nums[left] * k) {
                left++;
            }
            
            // Calculate how many elements are currently in our balanced window
            int currentWindowSize = right - left + 1;
            if (currentWindowSize > maxKept) {
                maxKept = currentWindowSize;
            }
        }

        // 3. Minimum removals is the total minus the most we can keep
        return n - maxKept;
    }
}