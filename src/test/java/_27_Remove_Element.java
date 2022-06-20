import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/remove-element/
 */
class _27_Remove_Element {
    int removeElement(int[] nums, int val) {
        var tail = 0;
        for (int scan = 0; scan < nums.length; scan++) {
            if (nums[scan] != val) {
                nums[tail] = nums[scan];
                tail++;
            }
        }
        return tail;
    }

    @Test
    void examples() {
        var input1 = new int[]{3, 2, 2, 3};
        assertEquals(2, removeElement(input1, 3));
        assertArrayEquals(new int[]{2, 2}, Arrays.stream(Arrays.copyOf(input1, 2)).sorted().toArray());

        var input2 = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        assertEquals(5, removeElement(input2, 2));
        assertArrayEquals(new int[]{0, 0, 1, 3, 4}, Arrays.stream(Arrays.copyOf(input2, 5)).sorted().toArray());
    }
}
