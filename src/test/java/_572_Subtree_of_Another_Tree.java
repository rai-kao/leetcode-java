import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/subtree-of-another-tree/">572. Subtree of Another Tree</a>
 */
class _572_Subtree_of_Another_Tree {
    boolean isIdentical(TreeNode root, TreeNode sub) {
        if (root == null && sub == null) {
            return true;
        }
        if (root == null || sub == null) {
            return false;
        }
        if (root.val != sub.val) {
            return false;
        }
        return isIdentical(root.left, sub.left) && isIdentical(root.right, sub.right);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (isIdentical(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    @Test
    void examples() {
        assertTrue(isSubtree(CommonUtils.arrayToTree(new Integer[]{3, 4, 5, 1, 2}), CommonUtils.arrayToTree(new Integer[]{4, 1, 2})));
        assertFalse(isSubtree(CommonUtils.arrayToTree(new Integer[]{3, 4, 5, 1, 2, null, null, null, null, 0}), CommonUtils.arrayToTree(new Integer[]{4, 1, 2})));
    }

    @Test
    void advance() {
        assertTrue(isSubtree(CommonUtils.arrayToTree(new Integer[]{3, 5, 4, null, null, 1, 4, null, null, null, null, null, null, 1, 2}), CommonUtils.arrayToTree(new Integer[]{4, 1, 2})));
    }
}
