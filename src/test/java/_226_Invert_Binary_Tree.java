import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/invert-binary-tree/">226. Invert Binary Tree</a>
 */
class _226_Invert_Binary_Tree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        var temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
    }

    @Test
    void examples() {
        assertArrayEquals(new Integer[]{4, 7, 2, 9, 6, 3, 1}, CommonUtils.levelOrderTraversal(invertTree(CommonUtils.arrayToTree(
                new Integer[]{4, 2, 7, 1, 3, 6, 9}))));
        assertArrayEquals(new Integer[]{2, 3, 1}, CommonUtils.levelOrderTraversal(invertTree(CommonUtils.arrayToTree(
                new Integer[]{2, 1, 3}))));
    }

    @Test
    void advance() {
        assertArrayEquals(new Integer[]{2, 3, 1, null, null, 0}, CommonUtils.levelOrderTraversal(invertTree(CommonUtils.arrayToTree(
                new Integer[]{2, 1, 3, null, 0}))));
    }
}
