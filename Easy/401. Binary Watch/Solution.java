import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> times = new ArrayList<>();
        
        // Optimization: Max possible bits for a valid time is 8 (3 for hours, 5 for minutes).
        // If turnedOn > 8, no solution exists.
        if (turnedOn > 8) return times;

        // Iterate through all possible times
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                
                // Efficiently count set bits
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    
                    // Format output: H:MM
                    StringBuilder time = new StringBuilder();
                    time.append(h).append(":");
                    if (m < 10) {
                        time.append("0");
                    }
                    time.append(m);
                    
                    times.add(time.toString());
                }
            }
        }
        
        return times;
    }
}