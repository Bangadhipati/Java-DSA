class Solution {
    public char findKthBit(int n, int k) {
        int length = (1 << n) - 1; // The length of Sn is 2^n - 1
        return findKthBitRecursive(length, k, false);
    }

    private char findKthBitRecursive(int length, int k, boolean invert) {
        // Base case: S1 is just "0"
        if (length == 1) {
            // If invert is true, return '1', else return '0'
            return invert ? '1' : '0';
        }

        int mid = length / 2 + 1; // Middle position

        if (k == mid) {
            // The middle bit is always '1' (unless inverted)
            return invert ? '0' : '1';
        } else if (k < mid) {
            // Search in the left half, length is now half, invert flag stays the same
            return findKthBitRecursive(length / 2, k, invert);
        } else {
            // Search in the right half.
            // Map the right index 'k' to its original position in the left half.
            // Because it is reversed, the new position is (length - k + 1).
            // We flip the 'invert' flag because the right side is inverted.
            return findKthBitRecursive(length / 2, length - k + 1, !invert);
        }
    }
}