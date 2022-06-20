import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
class _26_Remove_Duplicates_from_Sorted_Array {
    int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        var unique = 0;
        for (int scan = 1; scan < nums.length; scan++) {
            if (nums[unique] != nums[scan]) {
                unique++;
                nums[unique] = nums[scan];
            }
        }
        return unique + 1;
    }

    @Test
    void examples() {
        var input1 = new int[]{1, 1, 2};
        assertEquals(2, removeDuplicates(input1));
        assertArrayEquals(new int[]{1, 2}, Arrays.copyOf(input1, 2));

        var input2 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        assertEquals(5, removeDuplicates(input2));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, Arrays.copyOf(input2, 5));
    }

    @Test
    void advance() {
        var input1 = new int[]{-3, -1, 0, 0, 0, 3, 3};
        assertEquals(4, removeDuplicates(input1));
        assertArrayEquals(new int[]{-3, -1, 0, 3}, Arrays.copyOf(input1, 4));
    }
}
