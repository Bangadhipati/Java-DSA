class Solution {
    public String addBinary(String a, String b) {
        // Use StringBuilder for efficient string manipulation
        StringBuilder sb = new StringBuilder();
        
        // Pointers starting from the end of each string
        int i = a.length() - 1;
        int j = b.length() - 1;
        
        int carry = 0; // Holds the carry-over value (0 or 1)

        // Loop continues if there are digits left in 'a', OR 'b', OR we have a carry
        while (i >= 0 || j >= 0 || carry == 1) {
            
            // Get the digit from 'a' if index is valid, else treat as 0
            // (a.charAt(i) - '0') converts char '1' to int 1
            int digitA = (i >= 0) ? a.charAt(i) - '0' : 0;
            
            // Get the digit from 'b' if index is valid, else treat as 0
            int digitB = (j >= 0) ? b.charAt(j) - '0' : 0;

            // Calculate current sum
            int sum = digitA + digitB + carry;

            // Compute the new digit to append (sum % 2)
            // If sum is 2 (1+1), 2 % 2 = 0. 
            // If sum is 3 (1+1+1), 3 % 2 = 1.
            sb.append(sum % 2);

            // Compute the new carry (sum / 2)
            // If sum is 2 or 3, 2/2 = 1. Carry becomes 1.
            carry = sum / 2;

            // Move pointers to the left
            i--;
            j--;
        }

        // Since we appended numbers from right-to-left, the result is backward!
        // We must reverse it.
        return sb.reverse().toString();
    }
}