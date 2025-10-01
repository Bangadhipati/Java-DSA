class Solution {
    public int uniquePaths(int m, int n) {
        int totalSteps = m + n - 2;
        int downSteps = Math.min(m - 1, n - 1); // Choose the smaller for efficiency
        long result = 1;

        for (int i = 1; i <= downSteps; i++) {
            result = result * (totalSteps - i + 1) / i;
        }

        return (int) result;
    }
}
