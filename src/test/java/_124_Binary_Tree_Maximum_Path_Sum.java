import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/binary-tree-maximum-path-sum/">124. Binary Tree Maximum Path Sum</a>
 */
class _124_Binary_Tree_Maximum_Path_Sum {
    int max = Integer.MIN_VALUE;

    int pathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        var l = pathSum(node.left);
        var r = pathSum(node.right);
        max = Math.max(max, Math.max(node.val, Math.max(l + node.val + r, Math.max(node.val + l, node.val + r))));
        return Math.max(node.val, Math.max(node.val + l, node.val + r));
    }

    int maxPathSum(TreeNode root) {
        pathSum(root);
        return max;
    }

    @Test
    void examples() {
        assertEquals(6, maxPathSum(CommonUtils.arrayToTree(new Integer[]{1, 2, 3})));
        assertEquals(42, maxPathSum(CommonUtils.arrayToTree(new Integer[]{-10, 9, 20, null, null, 15, 7})));
    }

    @Test
    void advance() {
        assertEquals(2, maxPathSum(CommonUtils.arrayToTree(new Integer[]{2, -1, -2})));
    }
}
