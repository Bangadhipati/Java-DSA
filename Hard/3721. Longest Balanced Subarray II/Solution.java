class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            // Frequency array for current window starting at i
            int[] freq = new int[26];
            int distinctCount = 0;
            int maxFreq = 0;

            for (int j = i; j < n; j++) {
                int idx = chars[j] - 'a';
                
                if (freq[idx] == 0) {
                    distinctCount++;
                }
                freq[idx]++;
                
                // Track the highest frequency of any character in this window
                if (freq[idx] > maxFreq) {
                    maxFreq = freq[idx];
                }

                // Current window length
                int currentLen = j - i + 1;

                // Mathematical Check:
                // If a substring is balanced, every distinct character appears 'F' times.
                // Therefore, Total Length must equal (Distinct Count * Max Frequency).
                if (maxFreq * distinctCount == currentLen) {
                    if (currentLen > maxLen) {
                        maxLen = currentLen;
                    }
                }
            }
        }
        
        return maxLen;
    }
}