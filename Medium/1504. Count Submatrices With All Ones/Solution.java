class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] heights = new int[n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            // build histogram for current row
            for (int j = 0; j < n; j++) {
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
            }

            // stack-based method to count submatrices for this row
            ans += countRectangles(heights);
        }

        return ans;
    }

    private int countRectangles(int[] heights) {
        int n = heights.length;
        int[] sum = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                int prev = stack.peek();
                sum[i] = sum[prev] + heights[i] * (i - prev);
            } else {
                sum[i] = heights[i] * (i + 1);
            }

            stack.push(i);
            res += sum[i];
        }

        return res;
    }
}
