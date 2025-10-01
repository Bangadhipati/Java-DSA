import java.util.*;

class Solution {
    private static final String[] MAPPING = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return Collections.emptyList();
        
        // Calculate total number of combinations
        int total = 1;
        for (char c : digits.toCharArray()) {
            total *= MAPPING[c - '0'].length();
        }
        
        // Preallocate output array
        String[] results = new String[total];
        char[] path = new char[digits.length()];
        int[] index = {0}; // mutable int reference for DFS fill
        
        dfs(digits, 0, path, results, index);
        
        return Arrays.asList(results);
    }
    
    private void dfs(String digits, int depth, char[] path, String[] results, int[] idx) {
        if (depth == digits.length()) {
            results[idx[0]++] = new String(path);
            return;
        }
        String letters = MAPPING[digits.charAt(depth) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            path[depth] = letters.charAt(i);
            dfs(digits, depth + 1, path, results, idx);
        }
    }
}
