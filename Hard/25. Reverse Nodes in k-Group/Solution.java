class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupEnd = dummy;
        ListNode end = dummy;

        while (true) {
            // Move 'end' pointer to the end of the current k-group
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break; // Not enough nodes to reverse

            ListNode start = prevGroupEnd.next;
            ListNode nextGroupStart = end.next;
            end.next = null; // Temporarily break the list

            // Reverse the k-group
            prevGroupEnd.next = reverse(start);
            start.next = nextGroupStart;

            // Move prevGroupEnd to the end of the reversed group
            prevGroupEnd = start;
            end = prevGroupEnd;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}
