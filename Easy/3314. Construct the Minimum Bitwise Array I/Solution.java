class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int target = nums.get(i);
            
            // Prime 2 is the only even prime. 
            // x | (x+1) for x=0 is 1, x=1 is 1. No way to get 2.
            if (target == 2) {
                ans[i] = -1;
                continue;
            }
            
            // For odd primes, find the first 0-bit from the right.
            // nums[i] in binary looks like ...0111
            // To minimize ans[i], we want to turn the ...0111 into ...0011
            // This is because (...0011) OR (...0011 + 1) 
            // = (...0011) OR (...0100) = ...0111
            
            for (int b = 0; b <= 30; b++) {
                // Check if the b-th bit is 0
                if (((target >> b) & 1) == 0) {
                    // The bit to the right (b-1) is the one we flip to 0
                    // This creates the smallest x such that x | (x+1) == target
                    ans[i] = target ^ (1 << (b - 1));
                    break;
                }
            }
        }
        
        return ans;
    }
}