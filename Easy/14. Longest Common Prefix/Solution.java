class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        // Take the first word as reference
        String first = strs[0];
        
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            
            // Compare this character with all other strings
            for (int j = 1; j < strs.length; j++) {
                // If index is out of range OR characters mismatch
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return first.substring(0, i);
                }
            }
        }
        
        return first; // If we never found a mismatch
    }
}
