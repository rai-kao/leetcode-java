import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14425/Concise-O(log-N)-Binary-search-solution
 */
class _33_Search_in_Rotated_Sorted_Array {
    int findMin(int[] nums) {
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
        return l;
    }

    int search(int[] nums, int target) {
        if (nums.length == 1) {
            return target == nums[0] ? 0 : -1;
        }

        var min = findMin(nums);
        int ret;
        if (target >= nums[min] && target <= nums[nums.length - 1]) {
            ret = Arrays.binarySearch(nums, min, nums.length, target);
        } else {
            ret = Arrays.binarySearch(nums, 0, min, target);
        }
        return ret >= 0 ? ret : -1;
    }

    @Test
    void examples() {
        assertEquals(4, search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        assertEquals(-1, search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        assertEquals(-1, search(new int[]{1}, 0));
    }

    @Test
    void advance() {
        assertEquals(-1, search(new int[]{1}, 2));
        assertEquals(-1, search(new int[]{1, 3}, 2));
        assertEquals(1, search(new int[]{1, 3}, 3));
        assertEquals(0, search(new int[]{1, 3}, 1));
        assertEquals(0, search(new int[]{3, 1}, 3));
    }
}
