import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/graph-valid-tree/">261. Graph Valid Tree</a>
 * @see <a href="https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/261.%20Graph%20Valid%20Tree.md">Algorithm-and-Leetcode/leetcode/261. Graph Valid Tree.md</a>
 * @see <a href="https://www.lintcode.com/problem/178/description">178 · Graph Valid Tree</a>
 * @see <a href="https://jettlee.gitbooks.io/leetcode/content/261-graph-valid-tree.html">Graph Valid Tree</a>
 * <p>
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * <p>
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * <p>
 * Output: false
 */
class _261_Graph_Valid_Tree {
    int find(int[] nodes, int node) {
        if (nodes[node] == -1) {
            return node;
        }
        return find(nodes, nodes[node]);
    }

    public boolean validTree(int n, int[][] edges) {
        var nodes = new int[n];
        Arrays.fill(nodes, -1);

        for (var edge : edges) {
            var l = find(nodes, edge[0]);
            var r = find(nodes, edge[1]);
            if (l == r) {
                return false;
            }
            nodes[r] = l;
        }
        return edges.length == n - 1;
    }

    @Test
    void examples() {
        assertTrue(validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
        assertFalse(validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
    }

    @Test
    void advance() {
        assertTrue(validTree(1, new int[][]{}));
        assertFalse(validTree(2, new int[][]{}));
        assertFalse(validTree(3, new int[][]{}));
        assertFalse(validTree(3, new int[][]{{0, 1}}));
    }
}
