import java.util.*;

class Solution {
    private List<TreeNode> sortedNodes = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        // 1. Flatten the tree into a sorted list of nodes
        inorder(root);
        
        // 2. Build a balanced tree from the sorted list
        return buildBalancedTree(0, sortedNodes.size() - 1);
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        sortedNodes.add(node);
        inorder(node.right);
    }

    private TreeNode buildBalancedTree(int start, int end) {
        if (start > end) return null;

        // Choose the middle element as the root to ensure balance
        int mid = start + (end - start) / 2;
        TreeNode node = sortedNodes.get(mid);

        // Recursively construct left and right subtrees
        node.left = buildBalancedTree(start, mid - 1);
        node.right = buildBalancedTree(mid + 1, end);

        return node;
    }
}