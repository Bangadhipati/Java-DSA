class Solution {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap current element (0) with the low pointer
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // 1 is in the correct middle section, just move forward
                mid++;
            } else {
                // Swap current element (2) with the high pointer
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--;
                // Note: We don't increment mid here because the 
                // swapped element from high needs to be checked.
            }
        }
    }
}