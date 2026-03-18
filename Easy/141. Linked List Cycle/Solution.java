/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // An empty list or a list with only one node (and no loop) cannot have a cycle
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // As long as the fast pointer has a valid next step and a valid step after that
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move 1 step
            fast = fast.next.next;     // Move 2 steps
            
            // If they land on the exact same node, a cycle exists
            if (slow == fast) {
                return true;
            }
        }
        
        // If the fast pointer hits the end of the list, there is no cycle
        return false;
    }
}