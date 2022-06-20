import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/house-robber/">198. House Robber</a>
 */
class _198_House_Robber {
    public int rob(int[] nums) {
        var f0 = 0;
        var f1 = 0;
        var f2 = 0;
        var fn = 0;
        var max = 0;
        for (var i = 0; i < nums.length; i++) {
            fn = Math.max(f0, f1) + nums[i];
            max = Math.max(max, fn);
            f0 = f1;
            f1 = f2;
            f2 = fn;
        }
        return max;
    }

    @Test
    void examples() {
        assertEquals(4, rob(new int[]{1, 2, 3, 1}));
        assertEquals(12, rob(new int[]{2, 7, 9, 3, 1}));
    }

    @Test
    void advance() {
        assertEquals(4, rob(new int[]{2, 1, 1, 2}));
    }
}
