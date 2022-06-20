import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/maximum-product-subarray/">152. Maximum Product Subarray</a>
 * @see <a href="https://www.geeksforgeeks.org/maximum-product-subarray/">Maximum Product Subarray</a>
 */
class _152_Maximum_Product_Subarray {
    public int maxProduct(int[] nums) {
        var maxProd = nums[0];
        var minPrev = nums[0];
        var maxPrev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            var num = nums[i];
            var curMax = maxPrev * num;
            var curMin = minPrev * num;
            minPrev = Math.min(num, Math.min(curMax, curMin));
            maxPrev = Math.max(num, Math.max(curMax, curMin));
            maxProd = Math.max(maxProd, maxPrev);
        }
        return maxProd;
    }

    @Test
    void examples() {
        assertEquals(6, maxProduct(new int[]{2, 3, -2, 4}));
        assertEquals(0, maxProduct(new int[]{-2, 0, -1}));
    }

    @Test
    void advance() {
        assertEquals(-2, maxProduct(new int[]{-2}));
        assertEquals(2, maxProduct(new int[]{0, 2}));
        assertEquals(24, maxProduct(new int[]{2, -5, -2, -4, 3}));
    }
}
