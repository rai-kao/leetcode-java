import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/">235. Lowest Common Ancestor of a Binary Search Tree</a>
 */
class _235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    public TreeNode lowestCommonAncestorMidFirst(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if ((root.val - p.val) * (root.val - q.val) <= 0) {
            return root;
        }

        if (root.val > p.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        return lowestCommonAncestor(root.right, p, q);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (q.val < root.val && p.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    @Test
    void examples() {
        assertEquals(6, lowestCommonAncestor(CommonUtils.arrayToTree(new Integer[]
                {6, 2, 8, 0, 4, 7, 9, null, null, 3, 5}), new TreeNode(2), new TreeNode(8)).val);
        assertEquals(2, lowestCommonAncestor(CommonUtils.arrayToTree(new Integer[]
                {6, 2, 8, 0, 4, 7, 9, null, null, 3, 5}), new TreeNode(2), new TreeNode(4)).val);
        assertEquals(2, lowestCommonAncestor(CommonUtils.arrayToTree(new Integer[]
                {2, 1}), new TreeNode(2), new TreeNode(1)).val);
    }
}
