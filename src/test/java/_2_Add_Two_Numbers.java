import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/add-two-numbers/
 */
class _2_Add_Two_Numbers {
    ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode output = null;
        ListNode tail = null;
        ListNode lCursor = l1;
        ListNode rCursor = l2;
        int quotient = 0;
        while (lCursor != null || rCursor != null) {
            var sum = quotient + (lCursor != null ? lCursor.val : 0) + (rCursor != null ? rCursor.val : 0);
            quotient = sum / 10;
            var current = new ListNode(sum % 10);
            if (output == null) {
                output = tail = current;
            } else {
                tail.next = current;
                tail = current;
            }
            lCursor = lCursor != null ? lCursor.next : null;
            rCursor = rCursor != null ? rCursor.next : null;
        }
        if (quotient > 0) {
            tail.next = new ListNode(quotient);
        }
        return output;
    }

    @Test
    void examples() {
        assertArrayEquals(new int[]{7, 0, 8}, CommonUtils.listNodeToArray(addTwoNumbers(CommonUtils.arrayToListNode(new int[]{2, 4, 3}), CommonUtils.arrayToListNode(new int[]{5, 6, 4}))));
        assertArrayEquals(new int[]{0}, CommonUtils.listNodeToArray(addTwoNumbers(CommonUtils.arrayToListNode(new int[]{0}), CommonUtils.arrayToListNode(new int[]{0}))));
        assertArrayEquals(new int[]{8, 9, 9, 9, 0, 0, 0, 1}, CommonUtils.listNodeToArray(addTwoNumbers(CommonUtils.arrayToListNode(new int[]{9, 9, 9, 9, 9, 9, 9}), CommonUtils.arrayToListNode(new int[]{9, 9, 9, 9}))));
    }
}
