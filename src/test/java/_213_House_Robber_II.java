import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/house-robber-ii/">213. House Robber II</a>
 */
class _213_House_Robber_II {
    int rob(int[] nums, int start, int end) {
        var f0 = 0;
        var f1 = 0;
        var fn = nums[0];
        for (var i = start; i < end; i++) {
            fn = Math.max(f0 + nums[i], f1);
            f0 = f1;
            f1 = fn;
        }
        return fn;
    }

    public int rob(int[] nums) {
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));
    }

    @Test
    void examples() {
        assertEquals(3, rob(new int[]{2, 3, 2}));
        assertEquals(4, rob(new int[]{1, 2, 3, 1}));
        assertEquals(3, rob(new int[]{1, 2, 3}));

    }

    @Test
    void advance() {
        assertEquals(6, rob(new int[]{1, 2, 3, 4, 2}));
        assertEquals(1, rob(new int[]{1}));
        assertEquals(2, rob(new int[]{1, 2}));
        assertEquals(3, rob(new int[]{1, 2, 1, 1}));
        assertEquals(103, rob(new int[]{1, 3, 1, 3, 100}));
        assertEquals(340, rob(new int[]{200, 3, 140, 20, 10}));
    }
}
