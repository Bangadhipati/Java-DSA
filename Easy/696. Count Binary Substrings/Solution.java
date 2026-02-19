class Solution {
    public int countBinarySubstrings(String s) {
        // Convert to char array for the absolute fastest memory access
        char[] chars = s.toCharArray();
        
        int prevRun = 0;
        int curRun = 1;
        int totalCount = 0;
        
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                // If the character is the same, continue the current run
                curRun++;
            } else {
                // The character changed. We have a boundary.
                // Add the valid substrings from the previous boundary
                totalCount += Math.min(prevRun, curRun);
                
                // The current run now becomes the previous run
                prevRun = curRun;
                // Reset current run for the new character
                curRun = 1;
            }
        }
        
        // Don't forget to add the valid substrings from the final boundary
        totalCount += Math.min(prevRun, curRun);
        
        return totalCount;
    }
}