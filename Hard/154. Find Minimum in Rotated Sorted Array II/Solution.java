class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        // Loop while the search space is valid
        while (start < end) {
            
            // OPTIMIZATION: Early Exit
            // If the subarray is already sorted (start < end), the min is at 'start'.
            // This skips unnecessary binary search steps.
            if (nums[start] < nums[end]) {
                return nums[start];
            }

            // Unsigned bit shift is slightly cleaner for the CPU than division
            int mid = (start + end) >>> 1;

            if (nums[mid] > nums[end]) {
                // Min is to the right
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                // Min is to the left (including mid)
                end = mid;
            } else {
                // Duplicate handling: mid == end
                // We are unsure, but safe to remove the duplicate at 'end'
                end--;
            }
        }
        
        return nums[start];
    }
}