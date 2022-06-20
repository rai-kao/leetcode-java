import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/clone-graph/">133. Clone Graph</a>
 */
class _133_Clone_Graph {
    HashMap<Node, Node> visited = new HashMap<>();

    Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        var clone = new Node(node.val);
        visited.put(node, clone);
        for (var neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }

    @Test
    void examples() {
        assertTrue(CommonUtils.compare(new int[][]{
                        {2, 4}, {1, 3}, {2, 4}, {1, 3}},
                cloneGraph(CommonUtils.arrayToNode(new int[][]{
                        {2, 4}, {1, 3}, {2, 4}, {1, 3}}))));
        assertTrue(CommonUtils.compare(new int[][]{{}},
                cloneGraph(CommonUtils.arrayToNode(new int[][]{{}}))));
        assertTrue(CommonUtils.compare(new int[]{},
                cloneGraph(CommonUtils.arrayToNode(new int[]{}))));
    }
}
