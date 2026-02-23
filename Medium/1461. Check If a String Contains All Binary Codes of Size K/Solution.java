class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k; // We need to find exactly 2^k distinct codes
        int n = s.length();
        
        // Optimization 1: Mathematical impossibility check
        if (n < need + k - 1) {
            return false;
        }
        
        // Use a boolean array instead of a HashSet for O(1) tracking with zero GC overhead
        boolean[] seen = new boolean[need];
        int hash = 0;
        
        // Bitmask to extract only the last k bits. 
        // Example: if k=3, need = 8 (1000 in binary), allOnes = 7 (0111 in binary)
        int allOnes = need - 1; 
        
        // Optimization 2: toCharArray is faster than s.charAt(i) in a loop
        char[] chars = s.toCharArray();
        
        for (int i = 0; i < n; i++) {
            // Shift left, mask the last k bits, and add the new character bit
            hash = ((hash << 1) & allOnes) | (chars[i] - '0');
            
            // Wait until the window has expanded to length k
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    need--;
                    
                    // Optimization 3: Return immediately once we've found all required codes
                    if (need == 0) return true;
                }
            }
        }
        
        return false;
    }
}