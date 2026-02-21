class Solution {
    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        // Magic mask where the i-th bit is 1 if 'i' is a prime number.
        // Primes up to 20: 2, 3, 5, 7, 11, 13, 17, 19
        // Binary: 10100010100010101100 -> Decimal: 665772
        int magicMask = 665772;
        
        for (int i = left; i <= right; i++) {
            // Integer.bitCount uses a highly optimized CPU instruction (POPCNT)
            int setBits = Integer.bitCount(i);
            
            // Check if the 'setBits'-th position in our magic mask is 1
            if (((magicMask >> setBits) & 1) == 1) {
                count++;
            }
        }
        
        return count;
    }
}