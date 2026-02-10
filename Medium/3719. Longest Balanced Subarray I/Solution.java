class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        
        // seen[val] will store the 'start index' i to avoid full array resets.
        // This is a common trick: if seen[val] == currentStart, we know we've seen it.
        int[] seen = new int[100001];
        java.util.Arrays.fill(seen, -1);

        for (int i = 0; i < n; i++) {
            int evenDistinct = 0;
            int oddDistinct = 0;
            
            for (int j = i; j < n; j++) {
                int val = nums[j];
                
                // If we haven't seen this value for the current start index i
                if (seen[val] != i) {
                    seen[val] = i;
                    if (val % 2 == 0) evenDistinct++;
                    else oddDistinct++;
                }
                
                if (evenDistinct == oddDistinct) {
                    int currentLen = j - i + 1;
                    if (currentLen > maxLen) {
                        maxLen = currentLen;
                    }
                }
            }
        }
        
        return maxLen;
    }
}