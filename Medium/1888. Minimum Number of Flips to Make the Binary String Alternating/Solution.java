class Solution {
    public int minFlips(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        int mismatches1 = 0; // Tracks differences from "010101..."
        int mismatches2 = 0; // Tracks differences from "101010..."
        int minFlips = Integer.MAX_VALUE;
        
        // We simulate a string of 2 * n to account for all cyclic shifts
        for (int i = 0; i < 2 * n; i++) {
            
            // 1. Add the new character entering the right side of the window
            char c = chars[i % n];
            if (c != (i % 2 == 0 ? '0' : '1')) mismatches1++;
            if (c != (i % 2 == 0 ? '1' : '0')) mismatches2++;
            
            // 2. Remove the character that slipped out of the left side of the window
            if (i >= n) {
                // The absolute index of the character leaving is (i - n)
                char leftChar = chars[i % n]; // Same as chars[(i - n) % n]
                
                if (leftChar != ((i - n) % 2 == 0 ? '0' : '1')) mismatches1--;
                if (leftChar != ((i - n) % 2 == 0 ? '1' : '0')) mismatches2--;
            }
            
            // 3. Once the window reaches size n, record the minimum operations
            if (i >= n - 1) {
                minFlips = Math.min(minFlips, Math.min(mismatches1, mismatches2));
            }
        }
        
        return minFlips;
    }
}