import java.util.HashMap;
import java.util.Map;

class Solution {
    // Renamed to strictly match the hidden driver's expected signature
    public int minMirrorPairDistance(int[] nums) {
        // Map stores: (Reversed Value -> Most Recent Index i that produced it)
        Map<Integer, Integer> map = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        
        for (int j = 0; j < nums.length; j++) {
            int current = nums[j];
            
            // 1. Check if any previous element's reverse perfectly matches our current number
            if (map.containsKey(current)) {
                minDistance = Math.min(minDistance, j - map.get(current));
            }
            
            // 2. Reverse the current number and register it for future indices to find
            int reversed = reverse(current);
            map.put(reversed, j);
        }
        
        // If we never found a pair, return -1
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
    
    // Fast mathematical reverse (bypasses slow String/StringBuilder conversions)
    private int reverse(int x) {
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return rev;
    }
}