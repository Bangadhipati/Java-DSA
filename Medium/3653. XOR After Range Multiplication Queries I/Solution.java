class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        long MOD = 1000000007;
        
        // 1. Process each query
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            long v = q[3]; // Cast to long immediately to prevent downstream overflow
            
            // Apply the jump step 'k'
            for (int idx = l; idx <= r; idx += k) {
                // Safely multiply and modulo
                nums[idx] = (int) ((nums[idx] * v) % MOD);
            }
        }
        
        // 2. Calculate the final bitwise XOR of the entire array
        int finalXor = 0;
        for (int num : nums) {
            finalXor ^= num;
        }
        
        return finalXor;
    }
}