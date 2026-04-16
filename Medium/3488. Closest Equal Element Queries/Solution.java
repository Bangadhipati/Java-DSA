import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    // Return type updated to List<Integer> to satisfy the driver
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        
        // Find the maximum value to size our mapping array
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        // Primitive arrays acting as a highly-efficient Map<Integer, LinkedList<Integer>>
        int[] head = new int[maxVal + 1];
        Arrays.fill(head, -1);
        int[] next = new int[n];
        
        // Populate the linked list from right to left.
        // This naturally sorts the indices in ascending order!
        for (int i = n - 1; i >= 0; i--) {
            int v = nums[i];
            next[i] = head[v];
            head[v] = i;
        }
        
        int[] ans = new int[n];
        int[] temp = new int[n]; // Reusable array to hold the indices of a single number
        boolean[] seen = new boolean[maxVal + 1]; // To avoid processing the same number twice
        
        // Precompute the shortest circular distance for EVERY index in the array
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            
            if (!seen[v]) {
                seen[v] = true;
                int curr = head[v];
                
                // If the number only appears once, the answer is -1
                if (next[curr] == -1) {
                    ans[curr] = -1;
                    continue;
                }
                
                // Extract all indices for this value into the temp array
                int count = 0;
                while (curr != -1) {
                    temp[count++] = curr;
                    curr = next[curr];
                }
                
                // For each index, the closest match is either strictly to its left or right
                for (int j = 0; j < count; j++) {
                    // Calculate distance to the left neighbor (handling the circular wrap)
                    int distLeft = (j == 0) ? (temp[0] + n - temp[count - 1]) : (temp[j] - temp[j - 1]);
                    
                    // Calculate distance to the right neighbor (handling the circular wrap)
                    int distRight = (j == count - 1) ? (temp[0] + n - temp[count - 1]) : (temp[j + 1] - temp[j]);
                    
                    ans[temp[j]] = Math.min(distLeft, distRight);
                }
            }
        }
        
        // Process queries instantly using our precomputed ledger and pack into a List
        List<Integer> result = new ArrayList<>(queries.length);
        for (int i = 0; i < queries.length; i++) {
            result.add(ans[queries[i]]);
        }
        
        return result;
    }
}