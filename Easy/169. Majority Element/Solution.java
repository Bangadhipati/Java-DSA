class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // If the count drops to 0, pick the current number as the new candidate
            if (count == 0) {
                candidate = nums[i];
            }
            
            // If the current number is the candidate, increment the count. 
            // Otherwise, decrement it.
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        return candidate;
    }
}