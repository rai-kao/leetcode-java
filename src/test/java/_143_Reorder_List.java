import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/reorder-list/">143. Reorder List</a>
 */
class _143_Reorder_List {
    ListNode slow;

    ListNode reorder(ListNode fast) {
        // 1. Find middle
        if (fast == null) {
            return null;
        }

        var leftPrev = slow;
        slow = slow.next;
        if (fast.next == null) {
            // Break into two lists from middle
            leftPrev.next = null;
            return leftPrev;
        }

        var leftNext = reorder(fast.next.next);

        // 2. Merge from middle
        var rightHead = slow;
        slow = slow.next;
        rightHead.next = leftNext;
        leftPrev.next = rightHead;
        return leftPrev;
    }

    public void reorderList(ListNode head) {
        slow = head;
        reorder(head);
    }

    private ListNode sol3Helper(ListNode[] slow, ListNode fast) {
        ListNode left = slow[0];
        slow[0] = slow[0].next;
        if (fast.next == null) {
            left.next = null;
            return left;
        }

        ListNode head2 = (fast.next.next == null) ? null : sol3Helper(slow, fast.next.next);

        ListNode right = slow[0];
        slow[0] = slow[0].next;
        right.next = head2;
        left.next = right;
        return left;
    }

    public void reorderListStack(ListNode head) {
        var list = new ArrayList<ListNode>();
        var cursor = head;
        while (cursor != null) {
            list.add(cursor);
            cursor = cursor.next;
        }
        var l = 0;
        var r = list.size() - 1;
        while (l < r) {
            list.get(r).next = list.get(l).next;
            list.get(l).next = list.get(r);
            l++;
            r--;
        }
        list.get(l).next = null;
    }

    public void reorderList3Steps(ListNode head) {
        if (head == null) return;

        // find the middle of linked list [Problem 876]
        // in 1->2->3->4->5->6 find 4
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second part of the list [Problem 206]
        // convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4
        // reverse the second half in-place
        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        // merge two sorted linked lists [Problem 21]
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        ListNode first = head, second = prev;
        while (second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }

    @Test
    void examples() {
        int[] input;
        ListNode head;

        input = new int[]{1, 2, 3, 4};
        head = CommonUtils.arrayToListNode(input);
        reorderList(head);
        assertArrayEquals(new int[]{1, 4, 2, 3}, CommonUtils.listNodeToArray(head));

        input = new int[]{1, 2, 3, 4, 5};
        head = CommonUtils.arrayToListNode(input);
        reorderList(head);
        assertArrayEquals(new int[]{1, 5, 2, 4, 3}, CommonUtils.listNodeToArray(head));
    }
}
