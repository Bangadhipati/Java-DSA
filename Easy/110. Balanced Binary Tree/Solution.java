class Solution {
    public boolean isBalanced(TreeNode root) {
        // If checkHeight returns -1, the tree is unbalanced
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0;

        // Check left subtree
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1;

        // Check right subtree
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1;

        // If the current node is unbalanced, return -1
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Otherwise, return the height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }
}