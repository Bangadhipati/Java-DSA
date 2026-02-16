public class Solution {
    // Treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            // 1. Get the bit at position 'i' from the input
            int bit = (n >> i) & 1;
            
            // 2. Shift this bit to its new reversed position (31 - i)
            // 3. OR it into the result
            result = result | (bit << (31 - i));
        }
        
        return result;
    }
}