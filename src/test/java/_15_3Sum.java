import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/3sum/
 * https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
 */
class _15_3Sum {
    List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> zeroSum = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            var l = i + 1;
            var r = nums.length - 1;
            while (l < r) {
                var sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    zeroSum.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
            while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return zeroSum;
    }

    @Test
    void examples() {
        assertTrue(CommonUtils.compare(List.of(
                List.of(-1, -1, 2),
                List.of(-1, 0, 1)
        ), threeSum(new int[]{-1, 0, 1, 2, -1, -4})));
        assertTrue(CommonUtils.compare(List.of(), threeSum(new int[]{})));
        assertTrue(CommonUtils.compare(List.of(), threeSum(new int[]{0})));
    }

    @Test
    void advance() {
        assertTrue(CommonUtils.compare(List.of(List.of(0, 0, 0)), threeSum(new int[]{0, 0, 0})));
        assertTrue(CommonUtils.compare(List.of(), threeSum(new int[]{3, -2, 1, 0})));
        assertTrue(CommonUtils.compare(List.of(
                List.of(-2, -1, 3),
                List.of(-2, 0, 2),
                List.of(-1, 0, 1)
        ), threeSum(new int[]{3, 0, -2, -1, 1, 2})));
        assertTrue(CommonUtils.compare(List.of(
                List.of(-5, 1, 4),
                List.of(-4, 0, 4),
                List.of(-4, 1, 3),
                List.of(-2, -2, 4),
                List.of(-2, 1, 1),
                List.of(0, 0, 0)
        ), threeSum(new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0})));
        assertTrue(CommonUtils.compare(List.of(
                List.of(-82, -11, 93),
                List.of(-82, 13, 69),
                List.of(-82, 17, 65),
                List.of(-82, 21, 61),
                List.of(-82, 26, 56),
                List.of(-82, 33, 49),
                List.of(-82, 34, 48),
                List.of(-82, 36, 46),
                List.of(-70, -14, 84),
                List.of(-70, -6, 76),
                List.of(-70, 1, 69),
                List.of(-70, 13, 57),
                List.of(-70, 15, 55),
                List.of(-70, 21, 49),
                List.of(-70, 34, 36),
                List.of(-66, -11, 77),
                List.of(-66, -3, 69),
                List.of(-66, 1, 65),
                List.of(-66, 10, 56),
                List.of(-66, 17, 49),
                List.of(-49, -6, 55),
                List.of(-49, -3, 52),
                List.of(-49, 1, 48),
                List.of(-49, 2, 47),
                List.of(-49, 13, 36),
                List.of(-49, 15, 34),
                List.of(-49, 21, 28),
                List.of(-43, -14, 57),
                List.of(-43, -6, 49),
                List.of(-43, -3, 46),
                List.of(-43, 10, 33),
                List.of(-43, 12, 31),
                List.of(-43, 15, 28),
                List.of(-43, 17, 26),
                List.of(-29, -14, 43),
                List.of(-29, 1, 28),
                List.of(-29, 12, 17),
                List.of(-14, -3, 17),
                List.of(-14, 1, 13),
                List.of(-14, 2, 12),
                List.of(-11, -6, 17),
                List.of(-11, 1, 10),
                List.of(-3, 1, 2)
        ), threeSum(new int[]{34, 55, 79, 28, 46, 33, 2, 48, 31, -3, 84, 71, 52, -3, 93, 15, 21, -43, 57, -6, 86, 56, 94, 74, 83, -14, 28, -66, 46, -49, 62, -11, 43, 65, 77, 12, 47, 61, 26, 1, 13, 29, 55, -82, 76, 26, 15, -29, 36, -29, 10, -70, 69, 17, 49})));
    }
}
