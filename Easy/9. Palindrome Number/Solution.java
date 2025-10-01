class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers or numbers ending with 0 (except 0) can't be palindrome
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int reversedHalf = 0;
        
        while (x > reversedHalf) {
            int lastDigit = x % 10;
            reversedHalf = reversedHalf * 10 + lastDigit;
            x /= 10;
        }
        
        // For even digits: reversedHalf == x
        // For odd digits: reversedHalf/10 == x (middle digit ignored)
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
