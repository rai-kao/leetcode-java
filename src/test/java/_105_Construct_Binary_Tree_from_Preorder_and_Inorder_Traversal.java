import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @see <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">105. Construct Binary Tree from Preorder and Inorder Traversal</a>
 */
class _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    int[] advance0;
    int[] advance1;
    Integer[] advance2;

    int top(List<Integer> level, int l, int r) {
        var id = Integer.MAX_VALUE;
        for (int i = l; i < r; i++) {
            id = Math.min(id, level.get(i));
        }
        return id;
    }

    void build(TreeNode parent, List<Integer> rest, List<Integer> preorder, List<Integer> inorder, int lL, int lR, int rL, int rR, List<Integer> order) {
        if (rest.size() <= 0) {
            return;
        }

        if (lL < lR) {
            var pos = top(order, lL, lR);
            parent.left = new TreeNode(rest.remove(rest.indexOf(preorder.get(pos))));
            var mid = inorder.indexOf(parent.left.val);
            build(parent.left, rest, preorder, inorder, lL, mid, mid + 1, lR, order);
        }
        if (rL < rR) {
            var pos = top(order, rL, rR);
            parent.right = new TreeNode(rest.remove(rest.indexOf(preorder.get(pos))));
            var mid = inorder.indexOf(parent.right.val);
            build(parent.right, rest, preorder, inorder, rL, mid, mid + 1, rR, order);
        }
    }

    TreeNode buildTreeMine(int[] preorder, int[] inorder) {
        var root = new TreeNode(preorder[0]);
        var preList = Arrays.stream(preorder).boxed().toList();
        var inList = Arrays.stream(inorder).boxed().toList();
        var mid = inList.indexOf(root.val);
        var order = new ArrayList<Integer>();
        for (var i : inList) {
            order.add(preList.indexOf(i));
        }
        build(root, new ArrayList<>(preList.subList(1, preList.size())), preList, inList, 0, mid, mid + 1, inList.size(), order);
        return root;
    }

    TreeNode buildTreeFast(int[] preorder, int[] inorder) {
        int[] pre = {0};
        int[] in = {0};

        return helper(preorder, pre, inorder, in, Integer.MAX_VALUE);
    }

    TreeNode helper(int[] preorder, int[] pre, int[] inorder, int[] in, int max) {
        if (pre[0] == preorder.length || in[0] == inorder.length || inorder[in[0]] == max) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[pre[0]]);
        pre[0]++;
        root.left = helper(preorder, pre, inorder, in, root.val);
        in[0]++;
        root.right = helper(preorder, pre, inorder, in, max);
        return root;
    }

    TreeNode build(int root, int left, int right, int[] preorder, int[] inorder) {
        if (root >= preorder.length || left > right) {
            return null;
        }
        var result = new TreeNode(preorder[root]);
        var mid = 0;
        for (int i = left; i <= right; i++) {
            if (inorder[i] == result.val) {
                mid = i;
            }
        }
        result.left = build(root + 1, left, mid - 1, preorder, inorder);
        result.right = build(root + mid - left + 1, mid + 1, right, preorder, inorder);
        return result;
    }

    TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(0, 0, preorder.length - 1, preorder, inorder);
    }

    void initialize() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("_105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal.properties");
        properties.load(inputStream);
        advance0 = Arrays.stream(properties.getProperty("advance0").split(",")).mapToInt(Integer::parseInt).toArray();
        advance1 = Arrays.stream(properties.getProperty("advance1").split(",")).mapToInt(Integer::parseInt).toArray();
        advance2 = Arrays.stream(properties.getProperty("advance2").split(",")).map(i -> {
            i = i.trim();
            return i.equals("null") ? null : Integer.parseInt(i);
        }).toArray(Integer[]::new);
    }

    @Test
    void examples() {
        assertArrayEquals(new Integer[]{3, 9, 20, null, null, 15, 7}, CommonUtils.levelOrderTraversal(buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7})));
        assertArrayEquals(new Integer[]{-1}, CommonUtils.levelOrderTraversal(buildTree(new int[]{-1}, new int[]{-1})));
    }

    @Test
    void advance() {
        assertDoesNotThrow(this::initialize);
        assertArrayEquals(new Integer[]{3, 9, 20, null, 7, 15}, CommonUtils.levelOrderTraversal(buildTree(new int[]{3, 9, 7, 20, 15}, new int[]{9, 7, 3, 15, 20})));
        assertArrayEquals(advance2, CommonUtils.levelOrderTraversal(buildTree(advance0, advance1)));
    }
}
