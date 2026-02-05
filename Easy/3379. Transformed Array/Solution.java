class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            // If nums[i] is 0, result[i] is nums[i] (no movement)
            if (nums[i] == 0) {
                result[i] = nums[i];
            } else {
                // Calculate landing index using circular property
                // (i + nums[i]) % n handles the wrap around
                // Adding n and taking % n again handles negative indices
                int targetIndex = (i + nums[i]) % n;
                if (targetIndex < 0) {
                    targetIndex += n;
                }
                result[i] = nums[targetIndex];
            }
        }

        return result;
    }
}