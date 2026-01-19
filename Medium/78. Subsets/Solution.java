import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // Pre-calculating size (2^n) to optimize memory allocation
        int n = nums.length;
        int totalSubsets = 1 << n; 
        List<List<Integer>> result = new ArrayList<>(totalSubsets);
        
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, int start) {
        // Every state reached is a valid subset
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]); // Choose
            backtrack(result, current, nums, i + 1); // Explore
            current.remove(current.size() - 1); // Un-choose (Backtrack)
        }
    }
}