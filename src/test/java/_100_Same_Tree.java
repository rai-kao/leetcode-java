import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/same-tree/">100. Same Tree</a>
 * @see <a href="https://www.geeksforgeeks.org/level-order-tree-traversal/">Level Order Binary Tree Traversal</a>
 */
class _100_Same_Tree {
    boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    @Test
    void examples() {
        assertEquals(true, isSameTree(CommonUtils.arrayToTree(new Integer[]{}), CommonUtils.arrayToTree(new Integer[]{})));
        assertEquals(true, isSameTree(CommonUtils.arrayToTree(new Integer[]{1, 2, 3}), CommonUtils.arrayToTree(new Integer[]{1, 2, 3})));
        assertEquals(false, isSameTree(CommonUtils.arrayToTree(new Integer[]{1, 2}), CommonUtils.arrayToTree(new Integer[]{1, null, 2})));
        assertEquals(false, isSameTree(CommonUtils.arrayToTree(new Integer[]{1, 2, 1}), CommonUtils.arrayToTree(new Integer[]{1, 1, 2})));
    }
}
