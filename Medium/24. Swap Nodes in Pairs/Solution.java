class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = head.next;
        ListNode prev = null;

        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            ListNode nextPair = second.next;

            // Swap
            second.next = first;
            first.next = nextPair;
            if (prev != null) prev.next = second;

            // Move forward
            prev = first;
            head = nextPair;
        }

        return newHead;
    }
}
