class Solution {
    public int removeDuplicates(int[] nums) {
        // If the array has 2 or fewer elements, it already meets the criteria
        if (nums.length <= 2) {
            return nums.length;
        }

        // 'i' is the pointer for the position of the next valid element
        // We can always keep the first two elements
        int i = 2;

        for (int n = 2; n < nums.length; n++) {
            // Compare current element with the element two positions back
            // in the 'valid' portion of the array.
            if (nums[n] != nums[i - 2]) {
                nums[i] = nums[n];
                i++;
            }
        }

        return i;
    }
}