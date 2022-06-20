import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/linked-list-cycle/>141. Linked List Cycle</a>
 */
class _141_Linked_List_Cycle {
    boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        var slow = head;
        var fast = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    @Test
    void examples() {
        assertTrue(hasCycle(CommonUtils.arrayToCycleListNode(new int[]{3, 2, 0, -4}, 1)));
        assertTrue(hasCycle(CommonUtils.arrayToCycleListNode(new int[]{1, 2}, 0)));
        assertFalse(hasCycle(CommonUtils.arrayToCycleListNode(new int[]{1}, -1)));
    }

    @Test
    void advance() {
        assertFalse(hasCycle(CommonUtils.arrayToCycleListNode(new int[]{}, -1)));
        assertFalse(hasCycle(CommonUtils.arrayToCycleListNode(new int[]{1, 2}, -1)));
    }
}
