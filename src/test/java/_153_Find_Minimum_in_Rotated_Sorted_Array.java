import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/">153. Find Minimum in Rotated Sorted Array</a>
 */
public class _153_Find_Minimum_in_Rotated_Sorted_Array {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int min = l + (r - l) / 2;
            if (nums[min] > nums[r]) {
                l = min + 1;
            } else {
                r = min;
            }
        }
        return nums[l];
    }

    @Test
    void examples() {
        assertEquals(1, findMin(new int[]{3, 4, 5, 1, 2}));
        assertEquals(0, findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        assertEquals(11, findMin(new int[]{11, 13, 15, 17}));
    }
}
