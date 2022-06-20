import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/serialize-and-deserialize-binary-tree/">297. Serialize and Deserialize Binary Tree</a>
 */
class _297_Serialize_and_Deserialize_Binary_Tree {
    public class Codec {
        void serial(TreeNode root, StringBuilder buffer) {
            if (root == null) {
                buffer.append(",");
                return;
            }

            buffer.append(root.val).append(",");
            serial(root.left, buffer);
            serial(root.right, buffer);
        }

        TreeNode deserial(Queue<String> path, TreeNode root) {
            var value = path.poll();
            if (value == null || value.isEmpty()) {
                return null;
            }
            var node = new TreeNode(Integer.parseInt(value));
            node.left = deserial(path, root);
            node.right = deserial(path, root);
            return node;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            var output = new StringBuilder();
            serial(root, output);
            return output.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            var path = new LinkedList<>(List.of(data.split(",")));
            return deserial(path, null);
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));
    @Test
    void examples() {
        var ser = new Codec();
        var deser = new Codec();
        assertArrayEquals(new Integer[]{1, 2, 3, null, null, 4, 5}, CommonUtils.levelOrderTraversal(deser.deserialize((ser.serialize(CommonUtils.arrayToTree(new Integer[]{1, 2, 3, null, null, 4, 5}))))));
        assertArrayEquals(new Integer[]{}, CommonUtils.levelOrderTraversal(deser.deserialize((ser.serialize(CommonUtils.arrayToTree(new Integer[]{}))))));
    }
}
