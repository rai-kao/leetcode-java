import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/unique-paths/">62. Unique Paths</a>
 */
class _62_Unique_Paths {
    int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        var route = new int[n];
        Arrays.fill(route, 1);
        for (var row = 1; row < m; row++) {
            for (var col = 1; col < n; col++) {
                route[col] = route[col - 1] + route[col];
            }
        }
        return route[n - 1];
    }

    @Test
    void examples() {
        assertEquals(28, uniquePaths(3, 7));
        assertEquals(3, uniquePaths(3, 2));
    }
}
