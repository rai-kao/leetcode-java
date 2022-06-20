import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/maximum-subarray/">53. Maximum Subarray</a>
 * @see <a href="https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/">Largest Sum Contiguous Subarray</a>
 */
class _53_Maximum_Subarray {
    int maxSubArray(int[] nums) {
        var accMax = nums[0];
        var totalMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            accMax = Math.max(nums[i], nums[i] + accMax);
            totalMax = Math.max(accMax, totalMax);
        }

        return totalMax;
    }

    @Test
    void examples() {
        assertEquals(6, maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals(1, maxSubArray(new int[]{1}));
        assertEquals(23, maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }

    @Test
    void advance() {
        assertEquals(-1, maxSubArray(new int[]{-2, -1}));
        assertEquals(0, maxSubArray(new int[]{-1, 0, -2}));
        assertEquals(-1, maxSubArray(new int[]{-2, -1, -2}));
    }
}
