class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        if (n == 0) {
            return "";
        }
        
        int cols = n / rows;
        // Pre-allocate the StringBuilder capacity to avoid resizing overhead
        StringBuilder sb = new StringBuilder(n);
        
        // 1. Traverse every diagonal starting from the top row
        for (int j = 0; j < cols; j++) {
            
            // 2. Walk diagonally: row + 1, col + 1
            for (int r = 0, c = j; r < rows && c < cols; r++, c++) {
                
                // 3. Map the 2D coordinate back to the 1D string index
                sb.append(encodedText.charAt(r * cols + c));
            }
        }
        
        // 4. Strip the trailing spaces added by the cipher padding
        int end = sb.length() - 1;
        while (end >= 0 && sb.charAt(end) == ' ') {
            end--;
        }
        
        // Return the exactly trimmed original text
        return sb.substring(0, end + 1);
    }
}