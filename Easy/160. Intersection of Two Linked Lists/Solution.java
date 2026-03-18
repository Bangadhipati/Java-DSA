/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // If either list is empty, an intersection is physically impossible
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        // Loop until both pointers land on the exact same node in memory.
        // If they don't intersect, they will eventually both equal null at the same time.
        while (pA != pB) {
            // Move pA forward. If it hits the end, redirect it to the head of List B.
            pA = (pA == null) ? headB : pA.next;
            
            // Move pB forward. If it hits the end, redirect it to the head of List A.
            pB = (pB == null) ? headA : pB.next;
        }
        
        // Return the node where they met (or null if they never intersected)
        return pA;
    }
}