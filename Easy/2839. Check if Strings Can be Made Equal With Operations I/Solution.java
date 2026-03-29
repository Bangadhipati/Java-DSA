class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // Check if the even indices (0 and 2) contain the same characters in any order
        boolean evenMatch = (s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) ||
                            (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0));
        
        // Check if the odd indices (1 and 3) contain the same characters in any order
        boolean oddMatch = (s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3)) ||
                           (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1));
        
        // Both independent groups must match for the strings to be equal
        return evenMatch && oddMatch;
    }
}