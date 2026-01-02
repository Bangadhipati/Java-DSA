class Solution {
    public int climbStairs(int n) {
        // Base cases: if n is 1, answer is 1.
        if (n <= 1) {
            return 1;
        }
        
        // n = 1 has 1 way
        // n = 2 has 2 ways
        int prev2 = 1; // Represents n-2
        int prev1 = 2; // Represents n-1
        
        // We start calculating from step 3
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            
            // Shift our "window" up one step
            prev2 = prev1;   // The old "n-1" becomes the new "n-2"
            prev1 = current; // The current becomes the new "n-1"
        }
        
        return prev1;
    }
}