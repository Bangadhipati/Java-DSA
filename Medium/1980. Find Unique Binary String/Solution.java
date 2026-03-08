class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < nums.length; i++) {
            // Get the diagonal character at index i of string i
            char currentChar = nums[i].charAt(i);
            
            // Flip the bit and append to our result
            if (currentChar == '0') {
                result.append('1');
            } else {
                result.append('0');
            }
        }
        
        return result.toString();
    }
}