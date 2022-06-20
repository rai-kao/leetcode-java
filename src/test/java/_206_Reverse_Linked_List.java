import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/reverse-linked-list/">206. Reverse Linked List</a>
 */
class _206_Reverse_Linked_List {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        var cursor = head;
        var tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        ListNode temp;
        while (cursor != tail) {
            temp = cursor;
            cursor = cursor.next;
            temp.next = tail.next;
            tail.next = temp;
        }
        return tail;
    }

    @Test
    void examples() {
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, CommonUtils.listNodeToArray(reverseList(CommonUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5}))));
        assertArrayEquals(new int[]{2, 1}, CommonUtils.listNodeToArray(reverseList(CommonUtils.arrayToListNode(new int[]{1, 2}))));
        assertArrayEquals(new int[]{}, CommonUtils.listNodeToArray(reverseList(CommonUtils.arrayToListNode(new int[]{}))));
    }
}
