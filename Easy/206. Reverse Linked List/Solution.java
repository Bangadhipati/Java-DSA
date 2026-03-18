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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            // 1. Temporarily store the next node
            ListNode nextTemp = curr.next;
            
            // 2. Reverse the current node's pointer to point backward
            curr.next = prev;
            
            // 3. Slide the 'prev' pointer forward to the current node
            prev = curr;
            
            // 4. Slide the 'curr' pointer forward to the next node in line
            curr = nextTemp;
        }
        
        // When curr hits null, prev is resting on the final node (the new head)
        return prev;
    }
}