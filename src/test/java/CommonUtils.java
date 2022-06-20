import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class CommonUtils {
    public static boolean compare(List<?> a, List<?> b) {
        if (a.size() != b.size()) {
            return false;
        }

        for (var s : a) {
            if (s instanceof Collection) {
                if (b.stream().noneMatch(t -> CollectionUtils.isEqualCollection((Collection<?>) s, (Collection<?>) t))) {
                    return false;
                }
            } else {
                if (!CollectionUtils.containsAny(b, s)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static <T> boolean compare(T[][] a, T[][] b) {
        if (a.length != b.length) {
            return false;
        }

        return compare(arrayToList(a), arrayToList(b));
    }

    public static boolean compare(int[][] a, int[][] b) {
        if (a.length != b.length) {
            return false;
        }

        return compare(arrayToList(a), arrayToList(b));
    }

    public static <T> boolean compare(T[][] a, List<List<T>> b) {
        if (a.length != b.size()) {
            return false;
        }

        return compare(arrayToList(a), b);
    }

    public static boolean compare(int[] a, Node b) {
        return compare(arrayToList(a), nodeToList(b));
    }

    public static boolean compare(int[][] a, Node b) {
        return compare(arrayToList(a), nodeToList(b));
    }

    public static List<Integer> arrayToList(int[] array) {
        return Arrays.stream(array).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<?> arrayToList(int[][] array) {
        return Arrays.stream(array).map(a -> Arrays.stream(a).boxed().collect(Collectors.toList())).collect(Collectors.toCollection(ArrayList::new));
    }

    public static <T> List<?> arrayToList(T[][] array) {
        return Arrays.stream(array).map(a -> Arrays.stream(a).collect(Collectors.toList())).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ListNode arrayToListNode(int[] array) {
        ListNode head = null;
        ListNode tail = null;
        for (var i : array) {
            var node = new ListNode(i);
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    public static ListNode arrayToCycleListNode(int[] array, int pos) {
        ListNode head = null;
        ListNode tail = null;
        var map = new ArrayList<ListNode>();
        for (var i : array) {
            var node = new ListNode(i);
            map.add(node);
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        if (pos >= 0 && pos < array.length) {
            tail.next = map.get(pos);
        }
        return head;
    }

    public static ListNode[] arrayToListNode(int[][] array) {
        ListNode[] ret = new ListNode[array.length];
        for (int i = 0; i < array.length; i++) {
            ret[i] = arrayToListNode(array[i]);
        }
        return ret;
    }

    public static int[] listNodeToArray(ListNode list) {
        ListNode cursor = list;
        var output = new ArrayList<Integer>();
        while (cursor != null) {
            output.add(cursor.val);
            cursor = cursor.next;
        }
        return output.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void graphTraversal(Node node, HashSet<Node> visited, List<Object> adjList) {
        if (node == null) {
            return;
        }

        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        adjList.add(node.neighbors.stream().mapToInt(n -> n.val).boxed().toList());
        for (Node neighbor: node.neighbors) {
            graphTraversal(neighbor, visited, adjList);
        }
    }

    public static List<?> nodeToList(Node node) {
        if (node == null) {
            return new ArrayList<Node>();
        }

        var adjList = new ArrayList<>();
        var visited = new HashSet<Node>();
        graphTraversal(node, visited, adjList);
        return adjList;
    }

    public static Integer[] levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return new Integer[]{};
        }
        var output = new ArrayList<Integer>();
        var nodes = new LinkedList<TreeNode>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            var level = new LinkedList<TreeNode>();
            for (var node : nodes) {
                if (node != null) {
                    output.add(node.val);
                    level.add(node.left);
                    level.add(node.right);
                } else {
                    output.add(null);
                }
            }
            if (level.stream().allMatch(Objects::isNull)) {
                break;
            } else {
                nodes = level;
            }
        }

        var lastNotNull = output.size();
        for (int i = output.size() - 1; i >= 0 ; i--) {
            if (output.get(i) != null) {
                lastNotNull = i;
                break;
            }
        }
        return Arrays.copyOfRange(output.toArray(Integer[]::new), 0, lastNotNull + 1);
    }

    public static TreeNode arrayToTree(Integer[] tree) {
        if (tree.length <= 0) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        var root = new TreeNode(tree[0]);
        queue.add(root);
        var i = 1;
        while (!queue.isEmpty() && i < tree.length) {
            TreeNode tempNode = queue.poll();

            for (int j = 0; j < 2 && i < tree.length; j++, i++) {
                var node = tree[i] != null ? new TreeNode(tree[i]) : null;
                if (tempNode != null) {
                    if (j == 0) {
                        tempNode.left = node;
                    } else {
                        tempNode.right = node;
                    }
                }
                queue.add(node);
            }
        }
        return root;
    }

    public static Node arrayToNode(int[] nums) {
        return null;
    }

    public static Node arrayToNode(int[][] nums) {
        var map = new HashMap<Integer, Node>();
        Arrays.stream(nums).flatMapToInt(Arrays::stream).forEach(i -> map.put(i, new Node(i)));

        for (var i = 0; i < nums.length; i++) {
            var node = map.get(i + 1);
            for (var adj : nums[i]) {
                node.neighbors.add(map.get(adj));
            }
        }
        return map.size() > 0 ? map.get(1) : new Node(1);
    }
}
