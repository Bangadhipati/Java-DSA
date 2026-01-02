import java.util.*;

class Solution {
    // Global list to store final results
    List<String> res = new ArrayList<>();
    // Optimization: Store the max word length to avoid checking impossible substrings
    int maxWordLen = 0;

    public List<String> wordBreak(String s, List<String> wordDict) {
        // Use a Set for O(1) lookups
        Set<String> dict = new HashSet<>();
        for (String w : wordDict) {
            dict.add(w);
            maxWordLen = Math.max(maxWordLen, w.length());
        }

        // OPTIMIZATION 1: Pruning Dead Ends
        // Before we start the expensive path building, let's verify if the string 
        // is breakable at all. This is essentially solving "Word Break I" first.
        // If 's' cannot be broken, we return empty immediately.
        boolean[] canBreak = new boolean[s.length() + 1];
        canBreak[s.length()] = true; // Base case: end of string is reachable

        // Fill DP table from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length() && (j - i) <= maxWordLen; j++) {
                if (canBreak[j] && dict.contains(s.substring(i, j))) {
                    canBreak[i] = true;
                    break;
                }
            }
        }

        // If the start is not reachable from the end, give up now (0ms trick)
        if (!canBreak[0]) return res;

        // Start the actual path building
        backtrack(s, dict, 0, new StringBuilder());
        return res;
    }

    private void backtrack(String s, Set<String> dict, int index, StringBuilder currentSentence) {
        // Base Case: We reached the end of the string
        if (index == s.length()) {
            // Remove the trailing space and add to results
            res.add(currentSentence.toString().trim());
            return;
        }

        // Store original length to backtrack efficiently
        int len = currentSentence.length();

        // Loop through possible end indices
        // OPTIMIZATION 2: Don't go beyond maxWordLen
        for (int i = index; i < s.length() && (i - index) < maxWordLen; i++) {
            // Get substring from 'index' to 'i'
            String sub = s.substring(index, i + 1);

            if (dict.contains(sub)) {
                // Add word + space to current path
                currentSentence.append(sub).append(" ");
                
                // Recurse
                backtrack(s, dict, i + 1, currentSentence);
                
                // Backtrack: Reset StringBuilder to original length
                currentSentence.setLength(len);
            }
        }
    }
}