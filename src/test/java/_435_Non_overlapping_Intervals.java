import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/non-overlapping-intervals/">435. Non-overlapping Intervals</a>
 */
class _435_Non_overlapping_Intervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }
        Arrays.parallelSort(intervals, Comparator.comparingInt(a -> a[1]));
        var end = intervals[0][1];
        var result = 1;
        for (int i = 1; i < intervals.length; i++) {
            var cur = intervals[i];
            if (cur[0] >= end) {
                result++;
                end = cur[1];
            }
        }
        return intervals.length - result;
    }

    void quickSort(int[][] intervals, int start, int end) {
        if (start >= end) return;
        int left = start, right = end, pivot = intervals[(start + end) / 2][1];
        while (left <= right) {
            while (left <= right && intervals[left][1] < pivot) {
                left++;
            }
            while (left <= right && intervals[right][1] > pivot) {
                right--;
            }
            if (left <= right) {
                int[] tmp = intervals[left];
                intervals[left] = intervals[right];
                intervals[right] = tmp;
                left++;
                right--;
            }
        }
        quickSort(intervals, start, right);
        quickSort(intervals, left, end);
    }

    @Test
    void examples() {
        assertEquals(2, eraseOverlapIntervals(new int[][]{{1, 3}, {1, 2}, {1, 2}}));
        assertEquals(1, eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
        assertEquals(2, eraseOverlapIntervals(new int[][]{{1, 2}, {1, 2}, {1, 2}}));
        assertEquals(0, eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}}));
    }

    @Test
    void advance() {
        assertEquals(2, eraseOverlapIntervals(new int[][]{{0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}}));
        assertEquals(2, eraseOverlapIntervals(new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}}));
    }
}
