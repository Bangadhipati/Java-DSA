class Solution {
    public boolean rotateString(String s, String goal) {
        // 1. If lengths are different, rotation is mathematically impossible
        if (s.length() != goal.length()) {
            return false;
        }
        
        // 2. Double the original string
        String doubledString = s + s;
        
        // 3. Check if the goal exists anywhere inside the doubled string
        return doubledString.contains(goal);
    }
}