class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentVal) {
        // Base case: if node is null, it contributes 0 to the sum
        if (node == null) {
            return 0;
        }

        // Shift the current value left by 1 (multiply by 2) and add the current bit
        currentVal = (currentVal << 1) | node.val;

        // If it's a leaf node, return the completed binary number
        if (node.left == null && node.right == null) {
            return currentVal;
        }

        // Recursively sum the values from the left and right subtrees
        return dfs(node.left, currentVal) + dfs(node.right, currentVal);
    }
}