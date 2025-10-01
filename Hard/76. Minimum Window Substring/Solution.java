class Solution {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) return "";

        int[] need = new int[128]; // ASCII char counts for t
        int required = 0;
        for (char c : t.toCharArray()) {
            if (need[c]++ == 0) required++;
        }

        int[] have = new int[128];
        int formed = 0;
        int left = 0, minLen = Integer.MAX_VALUE, minStart = 0;

        for (int right = 0; right < m; right++) {
            char c = s.charAt(right);
            have[c]++;
            if (have[c] == need[c]) formed++;

            while (formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char lc = s.charAt(left++);
                if (--have[lc] < need[lc]) formed--;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
