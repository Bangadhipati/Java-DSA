class Solution {
    public int[] plusOne(int[] digits) {
        // 1. Loop backwards from the last digit
        for (int i = digits.length - 1; i >= 0; i--) {
            
            // 2. If digit is less than 9, just add 1 and return immediately
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            
            // 3. If digit is 9, it becomes 0
            digits[i] = 0;
        }
        
        // 4. Handle the edge case (e.g., 99 becomes 100)
        int[] newNumber = new int[digits.length + 1];
        newNumber[0] = 1;
        
        return newNumber;
    }
}