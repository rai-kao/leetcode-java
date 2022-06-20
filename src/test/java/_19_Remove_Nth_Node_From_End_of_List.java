import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * https://www.geeksforgeeks.org/remove-nth-node-from-end-of-the-linked-list/
 */
class _19_Remove_Nth_Node_From_End_of_List {
    /**
     * Others
     */
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode curr = head;
//        int len = 0;
//        while (curr != null) {
//            curr = curr.next;
//            len++;
//        }
//
//        if (len == n) {
//            return head.next;
//        }
//        curr = head;
//
//        for (int i = 0; i < len - n - 1; i++) {
//            curr = curr.next;
//        }
//        curr.next = curr.next.next;
//        return head;
//    }
    ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = head;
        ListNode target = head;
        ListNode next = head;
        ListNode cursor = head;
        int count = 0;
        while (cursor != null) {
            count++;
            if (count - (n - 1) > 0) {
                next = next.next;
            }
            if (count - n > 0) {
                target = target.next;
            }
            if (count - (n + 1) > 0) {
                prev = prev.next;
            }
            cursor = cursor.next;
        }

        // Head node
        if (prev == target) {
            head = next;
        } else {
            prev.next = next;
        }

        return head;
    }

    @Test
    void examples() {
        assertArrayEquals(new int[]{1, 2, 3, 5}, CommonUtils.listNodeToArray(removeNthFromEnd(CommonUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5}), 2)));
        assertArrayEquals(new int[]{}, CommonUtils.listNodeToArray(removeNthFromEnd(CommonUtils.arrayToListNode(new int[]{1}), 1)));
    }

    @Test
    void advance() {
        assertArrayEquals(new int[]{1}, CommonUtils.listNodeToArray(removeNthFromEnd(CommonUtils.arrayToListNode(new int[]{1, 2}), 1)));
    }
}
