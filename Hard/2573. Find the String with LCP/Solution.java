class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char currentChar = 'a';
        
        // Phase 1: Greedily construct the lexicographically smallest string
        for (int i = 0; i < n; i++) {
            if (word[i] == 0) { // If the character at 'i' is unassigned
                // If we exceed 'z', we have too many distinct character groups
                if (currentChar > 'z') {
                    return "";
                }
                
                // Assign the current letter to all identical characters based on LCP
                for (int j = i; j < n; j++) {
                    if (lcp[i][j] > 0 && word[j] == 0) {
                        word[j] = currentChar;
                    }
                }
                currentChar++; // Queue up the next letter in the alphabet
            }
        }
        
        // Phase 2: DP Verification (Iterating backwards)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expected = lcp[i][j];
                int actual = 0;
                
                // If the characters match, LCP is 1 + the LCP of the remaining suffixes
                if (word[i] == word[j]) {
                    if (i + 1 < n && j + 1 < n) {
                        actual = lcp[i + 1][j + 1] + 1;
                    } else {
                        actual = 1; // Boundary edge case
                    }
                }
                
                // If our generated string's DP state contradicts the input matrix
                if (actual != expected) {
                    return "";
                }
            }
        }
        
        return new String(word);
    }
}