import java.util.*;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        // Convert to a list to easily handle removals and replacements
        // ArrayList is fine here given the constraint N <= 50
        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);
        
        int ops = 0;
        
        while (true) {
            // Check if non-decreasing
            boolean sorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    sorted = false;
                    break;
                }
            }
            
            if (sorted) break;

            // Find leftmost pair with minimum sum
            int minSum = Integer.MAX_VALUE;
            int targetIdx = -1;
            
            for (int i = 0; i < list.size() - 1; i++) {
                int currentSum = list.get(i) + list.get(i + 1);
                if (currentSum < minSum) {
                    minSum = currentSum;
                    targetIdx = i;
                }
            }
            
            // Perform the merge: replace pair with their sum
            int sum = list.get(targetIdx) + list.get(targetIdx + 1);
            list.set(targetIdx, sum);
            list.remove(targetIdx + 1);
            
            ops++;
        }
        
        return ops;
    }
}