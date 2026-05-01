class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int f0 = 0;
        
        // 1. Calculate the total sum of the array and the initial F(0)
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += i * nums[i];
        }
        
        int maxF = f0;
        int currentF = f0;
        
        // 2. Mathematically derive F(k) from F(k-1)
        // We iterate from 1 to n-1. 
        // The element moving from the back to the front at step k is nums[n - k]
        for (int k = 1; k < n; k++) {
            currentF = currentF + sum - n * nums[n - k];
            maxF = Math.max(maxF, currentF);
        }
        
        return maxF;
    }
}