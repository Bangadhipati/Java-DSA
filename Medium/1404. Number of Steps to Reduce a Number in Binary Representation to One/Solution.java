class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        
        // Loop from the last character down to index 1
        for (int i = s.length() - 1; i > 0; i--) {
            int bit = (s.charAt(i) - '0') + carry;
            
            if (bit == 1) {
                // If it evaluates to 1, it's odd: add 1 (takes 1 step) and divide by 2 (takes 1 step)
                steps += 2;
                carry = 1;
            } else {
                // If it evaluates to 0 or 2, it acts as an even number: divide by 2 (takes 1 step)
                // If bit was 0, carry stays 0. If bit was 2, carry stays 1.
                steps += 1;
            }
        }
        
        // If we have a carry left over when reaching the most significant bit, 
        // it means the final number is effectively 2 (binary '10'), requiring one last division.
        return steps + carry;
    }
}