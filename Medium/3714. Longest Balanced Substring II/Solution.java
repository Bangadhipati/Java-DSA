import java.util.Arrays;

class Solution {
    public int longestBalanced(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if (n == 0) return 0;
        
        // 1. Check for 1 distinct character
        int maxLen = 1;
        int cur = 1;
        for (int i = 1; i < n; i++) {
            if (chars[i] == chars[i - 1]) cur++;
            else cur = 1;
            maxLen = Math.max(maxLen, cur);
        }
        
        // 2. Check for exactly 2 distinct characters
        maxLen = Math.max(maxLen, getMax2(chars, 'a', 'b'));
        maxLen = Math.max(maxLen, getMax2(chars, 'b', 'c'));
        maxLen = Math.max(maxLen, getMax2(chars, 'c', 'a'));
        
        // 3. Check for exactly 3 distinct characters
        maxLen = Math.max(maxLen, getMax3(chars));
        
        return maxLen;
    }

    // Helper for 2 distinct characters
    private int getMax2(char[] s, char c1, char c2) {
        int n = s.length;
        int maxLen = 0;
        // Array to store first occurrence of a prefix diff. Size 2N+1 centered at N.
        int[] first = new int[2 * n + 1];
        Arrays.fill(first, -2); // -2 implies unvisited
        
        // Mathematical trick to find the 3rd character to split by
        char third = (char)('a' + 'b' + 'c' - c1 - c2);
        
        int start = 0;
        while (start < n) {
            // Skip the third character
            if (s[start] == third) {
                start++;
                continue;
            }
            // Find boundaries of the segment containing only c1 and c2
            int end = start;
            while (end < n && s[end] != third) {
                end++;
            }
            
            int diff = 0;
            first[n] = start - 1; // Base difference of 0 occurs right before the segment starts
            
            for (int i = start; i < end; i++) {
                if (s[i] == c1) diff++;
                else diff--; // Must be c2
                
                if (first[n + diff] != -2) {
                    maxLen = Math.max(maxLen, i - first[n + diff]);
                } else {
                    first[n + diff] = i;
                }
            }
            
            // O(L) Cleanup for the next segment instead of O(N) Arrays.fill
            diff = 0;
            first[n] = -2;
            for (int i = start; i < end; i++) {
                if (s[i] == c1) diff++;
                else diff--;
                first[n + diff] = -2;
            }
            
            start = end;
        }
        return maxLen;
    }

    // Helper for 3 distinct characters
    private int getMax3(char[] s) {
        int n = s.length;
        CustomHashMap map = new CustomHashMap();
        int maxLen = 0;
        int countA = 0, countB = 0, countC = 0;
        
        map.put(0L, -1);
        
        for (int i = 0; i < n; i++) {
            if (s[i] == 'a') countA++;
            else if (s[i] == 'b') countB++;
            else countC++;
            
            int diff1 = countA - countB;
            int diff2 = countA - countC;
            
            // Encode the two diffs into a single 64-bit integer
            long key = ((long)diff1 << 32) | (diff2 & 0xFFFFFFFFL);
            
            int prev = map.get(key);
            if (prev != -2) {
                maxLen = Math.max(maxLen, i - prev);
            } else {
                map.put(key, i);
            }
        }
        return maxLen;
    }

    // Ultra-fast Custom Hash Map for primitive long -> int mapping
    static class CustomHashMap {
        static final int CAPACITY = 262144; // 2^18, safely handles up to 10^5 distinct states
        static final int MASK = CAPACITY - 1;
        long[] keys = new long[CAPACITY];
        int[] vals = new int[CAPACITY];
        
        public CustomHashMap() {
            Arrays.fill(vals, -2);
        }
        
        void put(long key, int val) {
            int idx = (int) (hash(key) & MASK);
            while (vals[idx] != -2 && keys[idx] != key) {
                idx = (idx + 1) & MASK; // Linear probing
            }
            if (vals[idx] == -2) {
                keys[idx] = key;
                vals[idx] = val;
            }
        }
        
        int get(long key) {
            int idx = (int) (hash(key) & MASK);
            while (vals[idx] != -2) {
                if (keys[idx] == key) return vals[idx];
                idx = (idx + 1) & MASK;
            }
            return -2;
        }
        
        // Fast mixing hash for 64-bit integers
        long hash(long key) {
            key ^= (key >>> 33);
            key *= 0xff51afd7ed558ccdL;
            key ^= (key >>> 33);
            return key;
        }
    }
}