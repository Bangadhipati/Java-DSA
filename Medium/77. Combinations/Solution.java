import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(k), 1, n, k);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int start, int n, int k) {
        // Base case: if the combination is done
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Optimization: Pruning
        // i <= n - (k - current.size()) + 1
        // This ensures there are enough numbers left to reach size k
        int remainingNeeded = k - current.size();
        for (int i = start; i <= n - remainingNeeded + 1; i++) {
            current.add(i); // Choose
            backtrack(result, current, i + 1, n, k); // Explore
            current.remove(current.size() - 1); // Un-choose (Backtrack)
        }
    }
}