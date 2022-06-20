import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * https://www.techiedelight.com/efficiently-merge-k-sorted-linked-lists/
 */
class _23_Merge_k_Sorted_Lists {
    public ListNode mergeKLists(ListNode[] lists) {
        return divideAndConquer(lists);
    }

    public ListNode sortedMerge(ListNode list1, ListNode list2) {
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

    ListNode merge(ListNode[] lists, int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            return lists[left];
        } else {
            int mid = left + (right - left) / 2;
            return sortedMerge(merge(lists, left, mid), merge(lists, mid + 1, right));
        }
    }

    /**
     * Using Divide and Conquer
     */
    ListNode divideAndConquer(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    /**
     * Use Min Heap
     *
     * @param lists
     * @return
     */
    ListNode minHeap(ListNode[] lists) {
        var heads = new PriorityQueue<ListNode>(Comparator.comparingInt(o -> o.val));
        for (var list : lists) {
            if (list != null) {
                heads.add(list);
            }
        }

        ListNode head = null;
        ListNode tail = null;
        while (!heads.isEmpty()) {
            var min = heads.poll();
            if (head == null) {
                head = tail = min;
            }
            if (min.next != null) {
                heads.add(min.next);
            }
            tail.next = min;
            tail = min;
            tail.next = null;
        }

        return head;
    }

    @Test
    void examples() {
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4, 5, 6}, CommonUtils.listNodeToArray(mergeKLists(CommonUtils.arrayToListNode(new int[][]{
                {1, 4, 5},
                {1, 3, 4},
                {2, 6}
        }))));
        assertArrayEquals(new int[]{}, CommonUtils.listNodeToArray(mergeKLists(CommonUtils.arrayToListNode(new int[][]{}))));
        assertArrayEquals(new int[]{}, CommonUtils.listNodeToArray(mergeKLists(CommonUtils.arrayToListNode(new int[][]{{}}))));
    }
}
