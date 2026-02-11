import java.util.Arrays;

class Solution {
    // Segment Tree Arrays: 4*N size to handle the tree structure
    private int[] treeMin;
    private int[] treeMax;
    private int[] lazy;
    private int n;

    public int longestBalanced(int[] nums) {
        n = nums.length;
        treeMin = new int[4 * n];
        treeMax = new int[4 * n];
        lazy = new int[4 * n];
        
        // Initialize tree with a "sentinel" value.
        // We use a large enough value that won't interfere with 0 checks,
        // but small enough to avoid overflow when adding lazy values.
        int INF = Integer.MAX_VALUE / 2; 
        Arrays.fill(treeMin, INF);
        Arrays.fill(treeMax, -INF);

        // Track the previous occurrence of each number
        int[] prevPos = new int[100001]; // Constraints say values <= 10^5
        Arrays.fill(prevPos, -1);

        int maxLen = 0;

        for (int r = 0; r < n; r++) {
            int val = nums[r];
            
            // 1. "Activate" the current index r in the tree.
            // Before processing the distinct contribution of nums[r],
            // a subarray starting at r and ending at r has 0 balance relative to itself initially.
            // We set it to 0, then immediately apply the update below.
            updatePoint(1, 0, n - 1, r, 0);

            // 2. Determine the update range and value.
            // If Even: adds +1 to distinct even count for subarrays starting after prevPos.
            // If Odd: adds -1 (effectively +1 to distinct odd count).
            int change = (val % 2 == 0) ? 1 : -1;
            int leftBound = prevPos[val] + 1;
            
            // Update the balance for all valid start points [leftBound, r]
            updateRange(1, 0, n - 1, leftBound, r, change);
            
            // 3. Update tracking for next time we see this number
            prevPos[val] = r;

            // 4. Find the leftmost index 'l' where the balance is 0.
            // This represents the longest valid subarray ending at 'r'.
            int l = findFirstZero(1, 0, n - 1);
            if (l != -1) {
                maxLen = Math.max(maxLen, r - l + 1);
            }
        }

        return maxLen;
    }

    // --- Segment Tree Operations ---

    private void push(int node) {
        if (lazy[node] != 0) {
            // Propagate to left child
            lazy[2 * node] += lazy[node];
            treeMin[2 * node] += lazy[node];
            treeMax[2 * node] += lazy[node];

            // Propagate to right child
            lazy[2 * node + 1] += lazy[node];
            treeMin[2 * node + 1] += lazy[node];
            treeMax[2 * node + 1] += lazy[node];

            // Clear current lazy
            lazy[node] = 0;
        }
    }

    private void updatePoint(int node, int start, int end, int idx, int val) {
        if (start == end) {
            treeMin[node] = val;
            treeMax[node] = val;
            lazy[node] = 0;
            return;
        }
        push(node);
        int mid = (start + end) / 2;
        if (idx <= mid) updatePoint(2 * node, start, mid, idx, val);
        else updatePoint(2 * node + 1, mid + 1, end, idx, val);
        
        treeMin[node] = Math.min(treeMin[2 * node], treeMin[2 * node + 1]);
        treeMax[node] = Math.max(treeMax[2 * node], treeMax[2 * node + 1]);
    }

    private void updateRange(int node, int start, int end, int l, int r, int val) {
        if (l > end || r < start) return;
        if (l <= start && end <= r) {
            treeMin[node] += val;
            treeMax[node] += val;
            lazy[node] += val;
            return;
        }
        push(node);
        int mid = (start + end) / 2;
        updateRange(2 * node, start, mid, l, r, val);
        updateRange(2 * node + 1, mid + 1, end, l, r, val);
        
        treeMin[node] = Math.min(treeMin[2 * node], treeMin[2 * node + 1]);
        treeMax[node] = Math.max(treeMax[2 * node], treeMax[2 * node + 1]);
    }

    // Efficiently find the smallest index 'i' in the range where value is 0.
    // Relies on the Intermediate Value Property: adjacent values differ by at most 1.
    private int findFirstZero(int node, int start, int end) {
        // Optimization: If 0 is not within the [min, max] range of this node,
        // it is impossible for a 0 to exist in this segment.
        if (treeMin[node] > 0 || treeMax[node] < 0) return -1;
        
        if (start == end) return start;
        
        push(node);
        int mid = (start + end) / 2;
        
        // Priority: Try left child first to get the smallest index (longest subarray)
        int res = findFirstZero(2 * node, start, mid);
        if (res != -1) return res;
        
        return findFirstZero(2 * node + 1, mid + 1, end);
    }
}