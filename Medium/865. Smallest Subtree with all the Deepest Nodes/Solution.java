/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    // Helper class to store both the depth and the deepest node (LCA) found so far
    class Result {
        TreeNode node;
        int depth;
        
        Result(TreeNode n, int d) {
            this.node = n;
            this.depth = d;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        // Base case: empty node has depth 0
        if (node == null) {
            return new Result(null, 0);
        }

        // Recursive step
        Result left = dfs(node.left);
        Result right = dfs(node.right);

        // Logic: Compare depths to decide which node to bubble up
        if (left.depth > right.depth) {
            // Left is deeper, so the answer is in the left subtree
            return new Result(left.node, left.depth + 1);
        } else if (right.depth > left.depth) {
            // Right is deeper, so the answer is in the right subtree
            return new Result(right.node, right.depth + 1);
        } else {
            // Both are equal depth, so THIS node is the LCA (pivot point)
            return new Result(node, left.depth + 1);
        }
    }
}