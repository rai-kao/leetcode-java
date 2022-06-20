import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst/">230. Kth Smallest Element in a BST</a>
 */
class _230_Kth_Smallest_Element_in_a_BST {
    Integer dfs(TreeNode root, int[] count) {
        if (root == null) {
            return null;
        }

        var l = dfs(root.left, count);
        if (l != null) {
            return l;
        }

        if (--count[0] == 0) {
            return root.val;
        }

        return dfs(root.right, count);
    }

    public int kthSmallest(TreeNode root, int k) {
        return dfs(root, new int[]{k});
    }

    @Test
    void examples() {
        assertEquals(1, kthSmallest(CommonUtils.arrayToTree(new Integer[]{3, 1, 4, null, 2}), 1));
        assertEquals(3, kthSmallest(CommonUtils.arrayToTree(new Integer[]{5, 3, 6, 2, 4, null, null, 1}), 3));
    }
}
