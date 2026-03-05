class Solution {
    public int minOperations(String s) {
        int countPattern1 = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            // Pattern 1 expects '0' at even indices and '1' at odd indices.
            // i % 2 evaluates to 0 for even, 1 for odd.
            // s.charAt(i) - '0' converts the character to the integer 0 or 1.
            // If they don't match, we need an operation.
            if (s.charAt(i) - '0' != i % 2) {
                countPattern1++;
            }
        }
        
        // The operations for Pattern 2 is simply (Total Length - Operations for Pattern 1)
        return Math.min(countPattern1, n - countPattern1);
    }
}