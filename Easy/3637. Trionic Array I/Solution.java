class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return false; // Minimum size to have p and q such that 0 < p < q < n-1

        int i = 0;

        // Phase 1: Strictly Increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        
        // If we didn't move at all, or we reached the end, it's not trionic
        if (i == 0 || i == n - 1) return false;
        int p = i; // First peak found

        // Phase 2: Strictly Decreasing
        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }
        
        // If we didn't move from p, or we reached the end, it's not trionic
        if (i == p || i == n - 1) return false;
        int q = i; // First valley found

        // Phase 3: Strictly Increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // We return true only if we reached the last index
        return i == n - 1;
    }
}