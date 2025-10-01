class Solution {
    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int leftGain = Math.max(dfs(node.left), 0);   // ignore negative paths
        int rightGain = Math.max(dfs(node.right), 0); // ignore negative paths

        // Path passing through this node
        maxSum = Math.max(maxSum, node.val + leftGain + rightGain);

        // Return max gain if path continues to parent
        return node.val + Math.max(leftGain, rightGain);
    }
}
