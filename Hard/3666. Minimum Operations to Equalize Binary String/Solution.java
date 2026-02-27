class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int z = 0;
        
        // 1. Count the initial number of zeros
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                z++;
            }
        }
        
        // If already all 1s, 0 operations needed
        if (z == 0) return 0;
        
        // 2. Iterate possible number of operations 'm'
        // The upper bound is safely n + 5 because the capacity check m*(n-k) 
        // guarantees a valid configuration within N steps if one exists.
        for (int m = 1; m <= n + 5; m++) {
            long mk = (long) m * k;
            
            // Constraint A: Parity of total flips must match parity of required zero flips
            if (mk % 2 != z % 2) continue;
            
            // Constraint B: We need at least enough flips to touch every '0' once
            if (mk < z) continue;
            
            // Constraint C: Maximum capacity constraint (Pigeonhole principle)
            if (m % 2 == 0) {
                if ((long) m * (n - k) >= z) {
                    return m;
                }
            } else {
                if ((long) m * (n - k) >= n - z) {
                    return m;
                }
            }
        }
        
        return -1; // Impossible to equalize
    }
}