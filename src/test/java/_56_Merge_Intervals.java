import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/merge-intervals/">56. Merge Intervals</a>
 */
class _56_Merge_Intervals {
    int[][] mergeWithoutSort(int[][] intervals) {
        var colors = IntStream.range(0, intervals.length).toArray();

        for (var i = 0; i < intervals.length; i++) {
            var paint = new HashSet<Integer>();
            var color = colors[i];
            for (var j = i; j < intervals.length; j++) {
                if (intervals[i][0] >= intervals[j][0] && intervals[i][0] <= intervals[j][1] ||
                        intervals[i][1] >= intervals[j][0] && intervals[i][1] <= intervals[j][1] ||
                        intervals[i][0] <= intervals[j][0] && intervals[i][1] >= intervals[j][0] ||
                        intervals[i][0] <= intervals[j][1] && intervals[i][1] >= intervals[j][1]) {
                    paint.add(colors[j]);
                    color = colors[j];
                }
            }
            for (int k = 0; k < colors.length; k++
            ) {
                if (paint.contains(colors[k])) {
                    colors[k] = color;
                }
            }
        }

        var ret = new HashMap<Integer, List<Integer>>();
        for (var i = 0; i < intervals.length; i++) {
            if (!ret.containsKey(colors[i])) {
                ret.put(colors[i], new ArrayList<>());
                ret.get(colors[i]).add(intervals[i][0]);
                ret.get(colors[i]).add(intervals[i][1]);
            } else {
                ret.get(colors[i]).add(intervals[i][0]);
                ret.get(colors[i]).add(intervals[i][1]);
                var min = Collections.min(ret.get(colors[i]));
                var max = Collections.max(ret.get(colors[i]));
                ret.put(colors[i], new ArrayList<>());
                ret.get(colors[i]).add(min);
                ret.get(colors[i]).add(max);
            }
        }

        return ret.values().stream().map(l -> l.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
    }

    int[][] mergeBitSet(int[][] intervals) {
        BitSet bit = new BitSet();
        int max = 0;
        for (int i = 0; i < intervals.length; i++) {
            int start = 2 * intervals[i][0];
            int end = 2 * intervals[i][1] + 1;
            max = Math.max(max, end);
            bit.set(start, end, true);
        }
        int cnt = 0;
        int index = 0;
        while (index < max) {
            int start = bit.nextSetBit(index);
            int end = bit.nextClearBit(start);
            intervals[cnt++] = new int[]{start / 2, (end - 1) / 2};
            index = end;
        }
        int[][] res = new int[cnt][2];
        for (int i = 0; i < cnt; i++) {
            res[i] = intervals[i];
        }
        return res;
    }

    int[][] mergeByBit(int[][] intervals) {
        int max = 0;
        for (int[] entry : intervals)
            max = Math.max(max, entry[1]);
        max = (max + 1) * 2;
        int[] bucket = new int[max];
        for (int[] entry : intervals) {
            bucket[entry[0] * 2]++;
            bucket[entry[1] * 2 + 1]--;
        }
        List<int[]> answer = new ArrayList<>();
        int start = 0;
        for (int end = 1; end < max; end++) {
            bucket[end] += bucket[end - 1];
            if (bucket[end] != 0 && bucket[end - 1] == 0)
                start = end;
            else if (bucket[end] == 0 && bucket[end - 1] != 0)
                answer.add(new int[]{start / 2, (end - 1) / 2});
        }
        return answer.toArray(new int[answer.size()][]);
    }

    int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        var result = new ArrayList<int[]>() {{
            add(intervals[0]);
        }};
        var last = intervals[0];
        for (var interval : intervals) {
            if (interval[0] <= last[1]) {
                last[1] = Math.max(interval[1], last[1]);
            } else {
                result.add(interval);
                last = interval;
            }
        }
        return result.toArray(int[][]::new);
    }

    @Test
    void examples() {
        assertTrue(CommonUtils.compare(new int[][]{
                {1, 6}, {8, 10}, {15, 18}
        }, merge(new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        })));

        assertTrue(CommonUtils.compare(new int[][]{
                {1, 5}
        }, merge(new int[][]{
                {1, 4}, {4, 5}
        })));
    }

    @Test
    void advance() {
        assertTrue(CommonUtils.compare(new int[][]{
                {0, 5}
        }, merge(new int[][]{
                {1, 4}, {0, 5}
        })));
        assertTrue(CommonUtils.compare(new int[][]{
                {1, 10}
        }, merge(new int[][]{
                {2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}
        })));
        assertTrue(CommonUtils.compare(new int[][]{
                {2, 7}
        }, merge(new int[][]{
                {2, 3}, {4, 6}, {5, 7}, {3, 4}
        })));
    }
}
