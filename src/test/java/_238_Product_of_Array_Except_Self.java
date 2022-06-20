import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/product-of-array-except-self/">238. Product of Array Except Self</a>
 */
class _238_Product_of_Array_Except_Self {
    public int[] productExceptSelf(int[] nums) {
        var res = new int[nums.length];
        res[nums.length - 1] = 1;
        for (var i = nums.length - 2; i >= 0; i--) {
            res[i] = res[i + 1] * nums[i + 1];
        }
        var prodL = 1;
        for (var i = 0; i < nums.length; i++) {
            res[i] *= prodL;
            prodL *= nums[i];
        }

        return res;
    }

    @Test
    void examples() {
        assertArrayEquals(new int[]{24, 12, 8, 6}, productExceptSelf(new int[]{1, 2, 3, 4}));
        assertArrayEquals(new int[]{0, 0, 9, 0, 0}, productExceptSelf(new int[]{-1, 1, 0, -3, 3}));
    }
}
