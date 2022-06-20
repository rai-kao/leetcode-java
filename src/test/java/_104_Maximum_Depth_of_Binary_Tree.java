import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">104. Maximum Depth of Binary Tree</a>
 */
class _104_Maximum_Depth_of_Binary_Tree {
    int depth(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        return Math.max(depth(node.left, depth + 1), depth(node.right, depth + 1));
    }

    int maxDepth(TreeNode root) {
        return depth(root, 0);
    }

    @Test
    void examples() {
        assertEquals(3, maxDepth(CommonUtils.arrayToTree(new Integer[]{3, 9, 20, null, null, 15, 7})));
        assertEquals(2, maxDepth(CommonUtils.arrayToTree(new Integer[]{1, null, 2})));
    }
}
