class Solution {
    public int mirrorDistance(int n) {
        int original = n;
        int reversed = 0;
        
        // Mathematically reverse the digits
        while (n > 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        
        // Return the absolute difference
        return Math.abs(original - reversed);
    }
}