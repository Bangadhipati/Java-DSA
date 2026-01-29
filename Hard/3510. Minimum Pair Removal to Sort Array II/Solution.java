import java.util.*;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        long[] val = new long[n];
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] deleted = new boolean[n];

        // Track how many adjacent pairs are out of order
        int inversions = 0;
        
        // Priority Queue stores: {sum, left_index}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]); // Leftmost tie-break
        });

        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            prev[i] = i - 1;
            next[i] = i + 1;
            if (i > 0 && val[i-1] > val[i]) inversions++;
        }
        next[n - 1] = -1;

        // Initialize PQ with all adjacent pairs
        for (int i = 0; i < n - 1; i++) {
            pq.offer(new long[]{val[i] + val[i + 1], i});
        }

        int operations = 0;
        while (inversions > 0 && !pq.isEmpty()) {
            long[] top = pq.poll();
            int i = (int) top[1];
            int j = next[i];

            // Validation: pair must be currently adjacent and sum must match
            if (j == -1 || deleted[i] || deleted[j] || (val[i] + val[j] != top[0])) continue;

            // Before merging, remove inversions caused by this pair and its neighbors
            if (prev[i] != -1 && val[prev[i]] > val[i]) inversions--;
            if (val[i] > val[j]) inversions--;
            if (next[j] != -1 && val[j] > val[next[j]]) inversions--;

            // Merge j into i
            val[i] += val[j];
            deleted[j] = true;
            
            // Re-link Doubly Linked List
            int jNext = next[j];
            next[i] = jNext;
            if (jNext != -1) prev[jNext] = i;

            // After merging, add new potential inversions
            if (prev[i] != -1 && val[prev[i]] > val[i]) inversions++;
            if (next[i] != -1 && val[i] > val[next[i]]) inversions++;

            // Add new pairs to PQ
            if (prev[i] != -1) pq.offer(new long[]{val[prev[i]] + val[i], prev[i]});
            if (next[i] != -1) pq.offer(new long[]{val[i] + val[next[i]], i});

            operations++;
        }

        return operations;
    }
}