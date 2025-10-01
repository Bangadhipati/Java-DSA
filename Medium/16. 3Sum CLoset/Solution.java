import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // Sort the array
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2]; // Initial sum

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // Update closestSum if the current sum is closer to target
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                if (sum < target) {
                    left++; // Need a bigger sum
                } else if (sum > target) {
                    right--; // Need a smaller sum
                } else {
                    // If sum == target, we found the closest possible
                    return sum;
                }
            }
        }
        return closestSum;
    }
}
