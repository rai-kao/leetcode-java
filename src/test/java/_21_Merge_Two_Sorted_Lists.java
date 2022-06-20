import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * https://www.geeksforgeeks.org/merge-two-sorted-linked-lists/
 */
class _21_Merge_Two_Sorted_Lists {
    ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return dummyNodes(list1, list2);
    }

    /**
     * Use dummy nodes
     */
    ListNode dummyNodes(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode tail = head;
        while (true) {
            if (list1 == null) {
                tail.next = list2;
                break;
            }
            if (list2 == null) {
                tail.next = list1;
                break;
            }

            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }

            tail = tail.next;
        }

        return head.next;
    }

    /**
     * Use local references
     */
    ListNode localReferences(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode tail = null;
        while (true) {
            if (list1 == null) {
                if (head == null) {
                    head = list2;
                } else {
                    tail.next = list2;
                }
                break;
            }
            if (list2 == null) {
                if (head == null) {
                    head = list1;
                } else {
                    tail.next = list1;
                }
                break;
            }

            ListNode smaller;
            if (list1.val <= list2.val) {
                smaller = list1;
                list1 = list1.next;
            } else {
                smaller = list2;
                list2 = list2.next;
            }

            if (head == null) {
                head = tail = smaller;
            } else {
                tail.next = smaller;
                tail = smaller;
            }
        }

        return head;
    }

    @Test
    void examples() {
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4}, CommonUtils.listNodeToArray(mergeTwoLists(CommonUtils.arrayToListNode(new int[]{1, 2, 4}), CommonUtils.arrayToListNode(new int[]{1, 3, 4}))));
        assertArrayEquals(new int[]{}, CommonUtils.listNodeToArray(mergeTwoLists(CommonUtils.arrayToListNode(new int[]{}), CommonUtils.arrayToListNode(new int[]{}))));
        assertArrayEquals(new int[]{0}, CommonUtils.listNodeToArray(mergeTwoLists(CommonUtils.arrayToListNode(new int[]{}), CommonUtils.arrayToListNode(new int[]{0}))));
    }
}
