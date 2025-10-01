class Solution {
    public boolean isNumber(String s) {
        int start = 0, end = s.length() - 1;
        while (start <= end && s.charAt(start) == ' ') start++;
        while (end >= start && s.charAt(end) == ' ') end--;
        if (start > end) return false;

        boolean digitSeen = false, dotSeen = false, expSeen = false, digitAfterExp = true;

        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                digitSeen = true;
                if (expSeen) digitAfterExp = true;
            } else if (c == '.') {
                if (dotSeen || expSeen) return false;
                dotSeen = true;
            } else if (c == 'e' || c == 'E') {
                if (expSeen || !digitSeen) return false;
                expSeen = true;
                digitAfterExp = false;
            } else if (c == '+' || c == '-') {
                if (i != start && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;
            } else return false;
        }

        return digitSeen && digitAfterExp;
    }
}
