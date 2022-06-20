import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/">323. Number of Connected Components in an Undirected Graph</a>
 * @see <a href="https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/323.%20Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph.md">Algorithm-and-Leetcode/leetcode/323. Number of Connected Components in an Undirected Graph.md</a>
 * @see <a href="https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/323-number-of-connected-components-in-an-undirected-graph.html">323. Number of Connected Components in an Undirected Graph</a>
 * <p>
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 * <p>
 * 0          3 <br>
 * |          | <br>
 * 1 --- 2    4 <br>
 * <p>
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 * <p>
 * 0           4 <br>
 * |           | <br>
 * 1 --- 2 --- 3 <br>
 * <p>
 * Output:  1
 * <ul>
 *     Note:
 *     <li>You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * </ul>
 */
class _323_Number_of_Connected_Components_in_an_Undirected_Graph {
    int find(int[] graph, int i) {
        if (graph[i] != -1) {
            return graph[i];
        }
        return i;
    }

    public int countComponents(int n, int[][] edges) {
        var graph = new int[n];
        Arrays.fill(graph, -1);
        var count = n;
        for (var edge : edges) {
            var l = find(graph, edge[0]);
            var r = find(graph, edge[1]);
            if (l != r) {
                count--;
            }
            graph[r] = l;
        }
        return count;
    }

    private int[] uf;

    public int countComponentsAmazon(int n, int[][] edges) {
        this.uf = new int[n];
        for (int i = 0; i < n; i++)
            uf[i] = i;
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }
        int count = 0;
        for (int i = 0; i < n; i++)
            if (uf[i] == i)
                count++;
        return count;
    }

    private int find(int i) {
        if (uf[i] != i) {
            uf[i] = find(uf[i]);
        }
        return uf[i];
    }

    private void union(int i, int j) {
        int x = find(i);
        int y = find(j);
        uf[x] = y;
    }

    @Test
    void examples() {
        assertEquals(2, countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
        assertEquals(2, countComponents(5, new int[][]{{0, 1}, {1, 2}, {0, 2}, {3, 4}}));
        assertEquals(2, countComponents(6, new int[][]{{0, 1}, {1, 2}, {3, 2}, {0, 3}, {4, 5}}));
        assertEquals(1, countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
    }
}
