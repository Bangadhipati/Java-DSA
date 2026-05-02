class Solution {
    public int rotatedDigits(int n) {
        int goodCount = 0;
        
        for (int i = 1; i <= n; i++) {
            if (isGoodNumber(i)) {
                goodCount++;
            }
        }
        
        return goodCount;
    }
    
    // Helper method to mathematically evaluate digits
    private boolean isGoodNumber(int num) {
        boolean hasTransformer = false;
        
        while (num > 0) {
            int digit = num % 10;
            
            // 1. Check for Saboteurs (Instantly Invalid)
            if (digit == 3 || digit == 4 || digit == 7) {
                return false; 
            }
            
            // 2. Check for Transformers (Guarantees the rotated number is different)
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                hasTransformer = true; 
            }
            
            // We don't need to explicitly check 0, 1, or 8. They just pass through.
            
            // Chop off the last digit to process the next one
            num /= 10;
        }
        
        // The number is only "good" if it had NO saboteurs and AT LEAST one transformer
        return hasTransformer;
    }
}