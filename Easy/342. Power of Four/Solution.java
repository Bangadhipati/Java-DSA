class Solution {
    public boolean isPowerOfFour(int n) {
        // Must be positive
        if (n <= 0) return false;

        // Check if it's a power of two: only one bit set in binary
        if ((n & (n - 1)) != 0) return false;

        // Check if that single '1' bit is at an even position (0-based)
        // 0x55555555 = binary pattern 010101...0101 (only even bit positions set)
        return (n & 0x55555555) != 0;
    }
}
