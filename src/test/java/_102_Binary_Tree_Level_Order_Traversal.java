import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/">102. Binary Tree Level Order Traversal</a>
 * @see <a href="https://www.geeksforgeeks.org/level-order-tree-traversal/">Level Order Binary Tree Traversal</a>
 */
class _102_Binary_Tree_Level_Order_Traversal {
    List<List<Integer>> levelOrder(TreeNode root) {
        var result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }

        var curr = new ArrayList<TreeNode>();
        var next = new ArrayList<TreeNode>();
        var level = new ArrayList<Integer>();
        curr.add(root);
        while (!curr.isEmpty()) {
            for (var node : curr) {
                level.add(node.val);

                if (node.left != null) {
                    next.add(node.left);
                }

                if (node.right != null) {
                    next.add(node.right);
                }
            }

            result.add(level);
            level = new ArrayList<>();
            curr = next;
            next = new ArrayList<>();
        }

        return result;
    }

    @Test
    void examples() {
        assertTrue(CommonUtils.compare(new Integer[][]{
                {3}, {9, 20}, {15, 7}
        }, levelOrder(CommonUtils.arrayToTree(new Integer[]{3, 9, 20, null, null, 15, 7}))));
        assertTrue(CommonUtils.compare(new Integer[][]{
                {1}
        }, levelOrder(CommonUtils.arrayToTree(new Integer[]{1}))));
        assertTrue(CommonUtils.compare(new Integer[][]{}, levelOrder(CommonUtils.arrayToTree(new Integer[]{}))));
    }
}
