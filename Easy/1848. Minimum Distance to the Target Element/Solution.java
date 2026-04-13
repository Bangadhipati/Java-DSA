class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        
        // Expand outward from distance 0 up to the maximum possible distance in the array
        for (int i = 0; i < n; i++) {
            
            // Check the right side of the start index
            if (start + i < n && nums[start + i] == target) {
                return i;
            }
            
            // Check the left side of the start index
            if (start - i >= 0 && nums[start - i] == target) {
                return i;
            }
        }
        
        // The constraints guarantee the target exists, so it will never reach this point
        return -1; 
    }
}