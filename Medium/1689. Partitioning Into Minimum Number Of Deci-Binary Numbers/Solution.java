class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;
        
        for (int i = 0; i < n.length(); i++) {
            // Convert character to integer value
            int currentDigit = n.charAt(i) - '0';
            
            if (currentDigit > maxDigit) {
                maxDigit = currentDigit;
                
                // Early exit: 9 is the highest possible digit. 
                // If we find it, we can't possibly need more partitions.
                if (maxDigit == 9) {
                    return 9;
                }
            }
        }
        
        return maxDigit;
    }
}