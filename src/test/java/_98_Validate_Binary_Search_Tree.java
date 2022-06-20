import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/validate-binary-search-tree/">98. Validate Binary Search Tree</a>
 * @see <a href="https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/">Tree Traversals (Inorder, Preorder and Postorder)</a>
 * @see <a href="https://www.geeksforgeeks.org/pre-order-post-order-and-in-order-traversal-of-a-binary-tree-in-one-traversal-using-recursion/">Pre Order, Post Order and In Order traversal of a Binary Tree in one traversal | (Using recursion)</a>
 * @see <a href="https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/">Inorder Tree Traversal without Recursion</a>
 */
class _98_Validate_Binary_Search_Tree {
    boolean isValid(TreeNode root, Stack<Integer> path) {
        if (root == null) {
            return true;
        }

        if (!isValid(root.left, path)) {
            return false;
        }

        if (!path.isEmpty() && root.val <= path.peek()) {
            return false;
        }
        path.push(root.val);

        return isValid(root.right, path);
    }

    boolean isValidBST(TreeNode root) {
        return isValid(root, new Stack<>());
    }

    @Test
    void examples() {
        assertEquals(true, isValidBST(CommonUtils.arrayToTree(new Integer[]{
                2, 1, 3
        })));
        assertEquals(false, isValidBST(CommonUtils.arrayToTree(new Integer[]{
                5, 1, 4, null, null, 3, 6
        })));
    }

    @Test
    void advance() {
        assertEquals(false, isValidBST(CommonUtils.arrayToTree(new Integer[]{
                5, 4, 6, null, null, 3, 7
        })));
        assertEquals(false, isValidBST(CommonUtils.arrayToTree(new Integer[]{
                2, 2, 2
        })));
    }
}
