class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }

    private int mergeSortAndCount(int[] nums, int left, int right) {
        if (left >= right) {
            return 0; // Base case: arrays of size 1 have 0 pairs
        }

        int mid = left + (right - left) / 2;
        
        // 1. Recursively count pairs in the left and right halves
        int count = mergeSortAndCount(nums, left, mid) + mergeSortAndCount(nums, mid + 1, right);
        
        // 2. Count cross-boundary pairs
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            // Keep sliding the right pointer as long as the condition holds.
            // MUST cast to long to prevent integer overflow on 2 * nums[j]
            while (j <= right && (long) nums[i] > 2L * nums[j]) {
                j++;
            }
            // Everything from (mid + 1) to (j - 1) forms a valid pair with nums[i]
            count += (j - (mid + 1));
        }
        
        // 3. Standard Merge step to keep the array sorted for the levels above
        merge(nums, left, mid, right);
        
        return count;
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= right) temp[k++] = nums[j++];
        
        for (int p = 0; p < temp.length; p++) {
            nums[left + p] = temp[p];
        }
    }
}