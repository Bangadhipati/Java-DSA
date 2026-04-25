class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int position = 0;
        int underscores = 0;
        
        // Sweep through the string exactly once
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            
            if (c == 'L') {
                position--; // Drifting Left
            } else if (c == 'R') {
                position++; // Drifting Right
            } else {
                underscores++; // Count the wildcards
            }
        }
        
        // The absolute position is our base distance. Add all wildcards to stretch it further.
        return Math.abs(position) + underscores;
    }
}