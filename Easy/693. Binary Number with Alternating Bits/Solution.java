class Solution {
    public boolean hasAlternatingBits(int n) {
        // 1. If bits alternate, n ^ (n >> 1) will be a sequence of 1s (e.g., 11111)
        int x = n ^ (n >> 1);
        
        // 2. Check if x is strictly a sequence of 1s.
        // If x is all 1s (e.g., 111), then x + 1 is a power of 2 (e.g., 1000).
        // Their bitwise AND must be 0.
        return (x & (x + 1)) == 0;
    }
}