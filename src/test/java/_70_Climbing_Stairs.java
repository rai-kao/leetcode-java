import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/climbing-stairs/">70. Climbing Stairs</a>
 * @see <a href="https://zh.wikipedia.org/wiki/%E6%96%90%E6%B3%A2%E9%82%A3%E5%A5%91%E6%95%B0">斐波那契数</a>
 */
class _70_Climbing_Stairs {
    long binomial(int n, int k) {
        var ret = 1L;
        for (int i = 0; i < k; i++) {
            ret = ret * (n - i) / (i + 1);
        }
        return ret;
    }

    int climbStairs(int n) {
        var count = (n / 2) + 1;
        var ret = 0L;
        for (int i = 0; i < count; i++) {
            ret += binomial(n - i, i);
        }

        return (int) ret;
    }

    @Test
    void examples() {
        assertEquals(2, climbStairs(2));
        assertEquals(3, climbStairs(3));
    }
}
