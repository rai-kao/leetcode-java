import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/missing-number/">268. Missing Number</a>
 */
class _268_Missing_Number {
    public int missingNumber(int[] nums) {
        var sum = nums.length;
        for (var i = 0; i < nums.length; i++) {
            sum += i - nums[i];
        }
        return sum;
    }

    @Test
    void examples() {
        assertEquals(2, missingNumber(new int[]{3, 0, 1}));
        assertEquals(2, missingNumber(new int[]{0, 1}));
        assertEquals(8, missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }
}
