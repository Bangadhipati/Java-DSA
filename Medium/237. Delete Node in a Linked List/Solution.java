/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        // 1. Copy the value from the next node into the current node
        node.val = node.next.val;
        
        // 2. Route the current node's pointer to completely bypass the next node
        node.next = node.next.next;
    }
}