class Solution {
    public int concatenatedBinary(int n) {
        long result = 0;
        int mod = 1_000_000_007;
        
        // This tracks the number of bits needed to represent the current number 'i'
        int bitLength = 0; 

        for (int i = 1; i <= n; i++) {
            // A number is a power of 2 if (i & (i - 1)) == 0.
            // If it is a power of 2, its binary representation needs one more bit.
            if ((i & (i - 1)) == 0) {
                bitLength++;
            }
            
            // 1. Shift the current result left by 'bitLength' spaces
            // 2. Use bitwise OR (|) to append 'i' into the newly created zeroed bits
            // 3. Apply modulo to prevent overflow
            result = ((result << bitLength) | i) % mod;
        }
        
        return (int) result;
    }
}