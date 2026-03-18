/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases (like deleting the head)
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        // 1. Move the fast pointer n steps ahead to create the gap
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // 2. Move both pointers at the same speed until fast reaches the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // 3. slow is now right before the node we want to delete. Bypass it.
        slow.next = slow.next.next;
        
        // Return the actual head of the list, which is safely stored after dummy
        return dummy.next;
    }
}