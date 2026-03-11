class Solution {
    public int bitwiseComplement(int n) {
        // Edge case: The binary representation of 0 is "0", so its complement is "1"
        if (n == 0) {
            return 1;
        }
        
        // Start with a mask of a single bit '1'
        int mask = 1;
        
        // Keep shifting left and adding 1 until the mask is strictly greater than or equal to n
        // For n = 5 (101), the mask will grow: 1 -> 3 (11) -> 7 (111)
        while (mask < n) {
            mask = (mask << 1) | 1;
        }
        
        // XOR n with the all-1s mask to flip exactly the bits we care about
        return n ^ mask;
    }
}