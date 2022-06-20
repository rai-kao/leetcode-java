import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/insert-interval/">57. Insert Interval</a>
 */
class _57_Insert_Interval {
    int[][] insert(int[][] intervals, int[] newInterval) {
        var result = new ArrayList<int[]>();
        var last = newInterval;
        final var start = 0;
        final var end = 1;
        for (var curr : intervals) {
            if (last[end] < curr[start]) {
                result.add(last);
                last = curr;
            } else if (curr[end] < last[start]) {
                result.add(curr);
            } else {
                last[start] = Math.min(curr[start], last[start]);
                last[end] = Math.max(curr[end], last[end]);
            }
        }
        result.add(last);
        return result.toArray(int[][]::new);
    }

    @Test
    void examples() {
        assertTrue(CommonUtils.compare(new int[][]{
                {1, 5}, {6, 9}
        }, insert(new int[][]{
                {1, 3}, {6, 9}
        }, new int[]{2, 5})));

        assertTrue(CommonUtils.compare(new int[][]{
                {1, 2}, {3, 10}, {12, 16}
        }, insert(new int[][]{
                {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        }, new int[]{4, 8})));
    }

    @Test
    void advance() {
        assertTrue(CommonUtils.compare(new int[][]{
                {5, 7}
        }, insert(new int[][]{
        }, new int[]{5, 7})));

        assertTrue(CommonUtils.compare(new int[][]{
                {1, 7}
        }, insert(new int[][]{
                {1, 5}
        }, new int[]{2, 7})));
    }
}
