class Solution {
    public int findDuplicate(int[] nums) {
        // Initialize both pointers at the starting index
        int slow = nums[0];
        int fast = nums[0];
        
        // Phase 1: Find the intersection point inside the cycle
        do {
            slow = nums[slow];           // Move 1 step
            fast = nums[nums[fast]];     // Move 2 steps
        } while (slow != fast);
        
        // Phase 2: Find the entrance to the cycle (the duplicate number)
        slow = nums[0];                  // Reset slow to the start
        
        while (slow != fast) {
            slow = nums[slow];           // Move 1 step
            fast = nums[fast];           // Move 1 step
        }
        
        // Both pointers now point to the duplicate number
        return slow;
    }
}