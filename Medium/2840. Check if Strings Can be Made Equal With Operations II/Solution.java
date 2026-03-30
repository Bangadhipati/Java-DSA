class Solution {
    public boolean checkStrings(String s1, String s2) {
        // Two frequency arrays for our independent groups
        int[] evenCounts = new int[26];
        int[] oddCounts = new int[26];
        
        int n = s1.length();
        
        // 1. Sweep through the strings and tally the differences
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                // Add for s1, subtract for s2
                evenCounts[s1.charAt(i) - 'a']++;
                evenCounts[s2.charAt(i) - 'a']--;
            } else {
                oddCounts[s1.charAt(i) - 'a']++;
                oddCounts[s2.charAt(i) - 'a']--;
            }
        }
        
        // 2. Verify that everything perfectly cancelled out
        for (int i = 0; i < 26; i++) {
            if (evenCounts[i] != 0 || oddCounts[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
}