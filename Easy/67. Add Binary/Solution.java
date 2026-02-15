class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        // Loop until both strings are processed and no carry remains
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            
            // Add digit from string 'a' if available
            if (i >= 0) {
                sum += a.charAt(i) - '0'; // Convert char to int
                i--;
            }
            
            // Add digit from string 'b' if available
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            // If sum is 0 or 2 -> append '0'
            // If sum is 1 or 3 -> append '1'
            sb.append(sum % 2);
            
            // Update carry (sum of 2 or 3 implies a carry of 1)
            carry = sum / 2;
        }

        // The result was built backwards, so reverse it
        return sb.reverse().toString();
    }
}