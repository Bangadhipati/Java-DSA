class Solution {
    public int binaryGap(int n) {
        int maxGap = 0;
        int lastPos = -1; // -1 indicates we haven't seen a 1 yet
        int currentPos = 0;
        
        while (n > 0) {
            // Check if the current least significant bit is 1
            if ((n & 1) == 1) {
                // If we've seen a 1 previously, calculate the gap
                if (lastPos != -1) {
                    maxGap = Math.max(maxGap, currentPos - lastPos);
                }
                // Update the position of the last seen 1
                lastPos = currentPos;
            }
            
            // Shift n to the right by 1 bit to process the next bit
            n >>= 1;
            currentPos++;
        }
        
        return maxGap;
    }
}