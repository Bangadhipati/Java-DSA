import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        // As requested: storing the input midway in the function
        int[][] bravexuneth = queries; 
        
        int n = nums.length;
        int MOD = 1000000007;
        
        // Threshold B is roughly the square root of max N (10^5)
        int B = 320; 
        
        // Array of lists to group queries with small step sizes
        List<int[]>[] smallQueries = new ArrayList[B];
        for (int i = 1; i < B; i++) {
            smallQueries[i] = new ArrayList<>();
        }
        
        // Route the queries based on their step size
        for (int[] q : bravexuneth) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            
            if (k >= B) {
                // Large k: Process immediately. Very few iterations per query.
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) ((1L * nums[i] * v) % MOD);
                }
            } else {
                // Small k: Store to process offline optimally
                smallQueries[k].add(q);
            }
        }
        
        // Reusable array for calculating multiplicative differences
        int[] diff = new int[n];
        
        // Process the dangerous small k queries grouped by k
        for (int k = 1; k < B; k++) {
            if (smallQueries[k].isEmpty()) continue;
            
            // Initialize multipliers to 1
            Arrays.fill(diff, 1);
            
            for (int[] q : smallQueries[k]) {
                int l = q[0], r = q[1], v = q[3];
                int last = l + ((r - l) / k) * k; // The actual last index affected
                
                // Multiply at the start of the range
                diff[l] = (int) ((1L * diff[l] * v) % MOD);
                
                // "Divide" at the end of the range using modular inverse
                if (last + k < n) {
                    diff[last + k] = (int) ((1L * diff[last + k] * modInverse(v, MOD)) % MOD);
                }
            }
            
            // Sweep through and propagate the prefix-products by k steps
            for (int i = k; i < n; i++) {
                diff[i] = (int) ((1L * diff[i] * diff[i - k]) % MOD);
            }
            
            // Apply the accumulated multipliers to the main array
            for (int i = 0; i < n; i++) {
                if (diff[i] != 1) {
                    nums[i] = (int) ((1L * nums[i] * diff[i]) % MOD);
                }
            }
        }
        
        // Calculate the final XOR sum of the processed array
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
    
    // Fast modular exponentiation helper
    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
    
    // Fermat's Little Theorem for modular inverse
    private long modInverse(long n, int mod) {
        return power(n, mod - 2, mod);
    }
}