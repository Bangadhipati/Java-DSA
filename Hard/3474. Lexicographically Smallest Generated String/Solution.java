import java.util.Arrays;

class Solution {
    // Renamed to match the platform's exact expected signature
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int totalLen = n + m - 1;
        char[] word = new char[totalLen];
        
        // Step 1: Apply 'T' mandatory constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    char expected = str2.charAt(j);
                    // If a character is already assigned and conflicts, return empty
                    if (word[i + j] != 0 && word[i + j] != expected) {
                        return ""; 
                    }
                    word[i + j] = expected;
                }
            }
        }
        
        // Identify remaining mutable (unassigned) positions
        int[] mutables = new int[totalLen];
        int mutCount = 0;
        int[] posToMutIndex = new int[totalLen];
        for (int i = 0; i < totalLen; i++) {
            if (word[i] == 0) {
                posToMutIndex[i] = mutCount;
                mutables[mutCount++] = i;
            } else {
                posToMutIndex[i] = -1; // -1 marks a position strictly locked by 'T'
            }
        }
        
        // Step 2: Organize 'F' constraints based on their LAST mutable position
        // Using a flattened Adjacency List for O(1) memory overhead
        int[] head = new int[totalLen];
        Arrays.fill(head, -1);
        int[] next = new int[n];
        int[] startIdxList = new int[n];
        int edgeCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                int lastMut = -1;
                
                // Find the rightmost mutable position in this F window
                for (int j = m - 1; j >= 0; j--) {
                    if (posToMutIndex[i + j] != -1) {
                        lastMut = i + j;
                        break;
                    }
                }
                
                if (lastMut == -1) {
                    // The entire window is locked. Check if it accidentally matches str2.
                    boolean match = true;
                    for (int j = 0; j < m; j++) {
                        if (word[i + j] != str2.charAt(j)) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        return ""; // An 'F' constraint is fatally violated by 'T' constraints
                    }
                } else {
                    // Register this F constraint to be checked when we assign lastMut
                    startIdxList[edgeCount] = i;
                    next[edgeCount] = head[lastMut];
                    head[lastMut] = edgeCount++;
                }
            }
        }
        
        // Step 3: DFS / Backtracking to assign lexicographically smallest valid characters
        int[] choice = new int[mutCount];
        int p = 0;
        
        while (p < mutCount) {
            // Exhausted all 'a' to 'z' letters for this position
            if (choice[p] == 26) {
                choice[p] = 0;         // Reset
                word[mutables[p]] = 0; // Unassign
                p--;                   // Backtrack
                
                // If we backtrack past the first mutable position, it's completely unsatisfiable
                if (p < 0) {
                    return ""; 
                }
                choice[p]++; // Try the next letter for the previous position
                continue;
            }
            
            char c = (char) ('a' + choice[p]);
            word[mutables[p]] = c;
            
            // Validate this choice against all F constraints ending at this exact position
            boolean valid = true;
            for (int e = head[mutables[p]]; e != -1; e = next[e]) {
                int startIdx = startIdxList[e];
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (word[startIdx + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                // If picking 'c' perfectly completed an unwanted str2 match
                if (match) {
                    valid = false;
                    break;
                }
            }
            
            if (valid) {
                p++; // Move forward to the next mutable position
            } else {
                choice[p]++; // Conflict detected, loop will try the next alphabet letter
            }
        }
        
        return new String(word);
    }
}