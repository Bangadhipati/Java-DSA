class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        
        // Convert to a primitive array for the fastest possible traversal
        for (char move : moves.toCharArray()) {
            if (move == 'U') {
                y++;
            } else if (move == 'D') {
                y--;
            } else if (move == 'R') {
                x++;
            } else if (move == 'L') {
                x--;
            }
        }
        
        // The robot is back at the origin if and only if there is zero net displacement
        return x == 0 && y == 0;
    }
}