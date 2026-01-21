import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int target = nums.get(i);

            // Special case: Only even prime is 2. 
            // x | (x+1) can never produce 2 (binary 10) 
            // because x | (x+1) always results in an odd number (trailing bit becomes 1).
            if (target == 2) {
                ans[i] = -1;
                continue;
            }

            // Find the length of the trailing 1s block.
            // We want to find the first '0' from the right.
            // The bit we need to flip to 0 is the bit immediately to the right of that 0.
            int temp = target;
            int bitPos = 0;
            
            // Count consecutive trailing 1s
            while ((temp & 1) == 1) {
                temp >>= 1;
                bitPos++;
            }
            
            // To minimize ans[i], we flip the highest bit of the trailing 1s block to 0.
            // Example: 10111 -> flip bit at (bitPos - 1) -> 10011
            ans[i] = target ^ (1 << (bitPos - 1));
        }

        return ans;
    }
}