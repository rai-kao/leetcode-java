import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/pacific-atlantic-water-flow/">417. Pacific Atlantic Water Flow</a>
 */
class _417_Pacific_Atlantic_Water_Flow {
    int m;
    int n;

    void dfs(int[][] heights, boolean[][] visited, int height, int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return;
        }
        if (heights[row][col] < height || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        dfs(heights, visited, heights[row][col], row - 1, col);
        dfs(heights, visited, heights[row][col], row, col + 1);
        dfs(heights, visited, heights[row][col], row + 1, col);
        dfs(heights, visited, heights[row][col], row, col - 1);
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        var pac = new boolean[m][n];
        var atl = new boolean[m][n];

        for (int i = 0; i < n; i++) {
            dfs(heights, pac, 0, 0, i);
            dfs(heights, atl, 0, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            dfs(heights, pac, 0, i, 0);
            dfs(heights, atl, 0, i, n - 1);
        }

        var ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j]) {
                    ret.add(List.of(i, j));
                }
            }
        }
        return ret;
    }

    @Test
    void examples() {
        assertTrue(CommonUtils.compare(new Integer[][]{
                {0, 4}, {1, 3}, {1, 4}, {2, 2}, {3, 0}, {3, 1}, {4, 0}}, pacificAtlantic(new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        })));

        assertTrue(CommonUtils.compare(new Integer[][]{
                {0, 0}, {0, 1}, {1, 0}, {1, 1}}, pacificAtlantic(new int[][]{
                {2, 1},
                {1, 2}
        })));
    }

    @Test
    void advance() {
        assertTrue(CommonUtils.compare(new Integer[][]{
                {0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 0}, {2, 1}}, pacificAtlantic(new int[][]{
                {1, 1}, {1, 1}, {1, 1}
        })));
    }
}
