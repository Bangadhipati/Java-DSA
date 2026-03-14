class Solution {
    public String getHappyString(int n, int k) {
        // Total happy strings of length n is 3 * 2^(n-1)
        int totalStrings = 3 * (1 << (n - 1));
        
        // If k is out of bounds, return an empty string
        if (k > totalStrings) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        k--; // Make k 0-indexed for easier math
        
        // 1. Determine the very first character
        int blockSize = 1 << (n - 1); // 2^(n-1)
        int charIndex = k / blockSize;
        char prevChar = (char) ('a' + charIndex);
        sb.append(prevChar);
        
        k %= blockSize; // Update k to the relative position inside the new block
        
        // 2. Determine the remaining (n - 1) characters
        for (int i = 1; i < n; i++) {
            blockSize = 1 << (n - 1 - i); // The block size halves each step
            charIndex = k / blockSize;    // Will always be 0 or 1
            
            // Figure out what the two valid alphabetical choices are
            char firstChoice = 'a';
            char secondChoice = 'b';
            
            if (prevChar == 'a') {
                firstChoice = 'b'; secondChoice = 'c';
            } else if (prevChar == 'b') {
                firstChoice = 'a'; secondChoice = 'c';
            } else { // prevChar == 'c'
                firstChoice = 'a'; secondChoice = 'b';
            }
            
            // Pick the character based on the calculated index
            if (charIndex == 0) {
                prevChar = firstChoice;
            } else {
                prevChar = secondChoice;
            }
            
            sb.append(prevChar);
            k %= blockSize; // Update k for the next level
        }
        
        return sb.toString();
    }
}