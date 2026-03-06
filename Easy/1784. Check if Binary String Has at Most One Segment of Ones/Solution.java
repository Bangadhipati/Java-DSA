class Solution {
    public boolean checkOnesSegment(String s) {
        // If the string contains "01", it guarantees a second segment of 1s has started.
        return !s.contains("01");
    }
}