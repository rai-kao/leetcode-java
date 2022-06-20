import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/longest-consecutive-sequence/">128. Longest Consecutive Sequence</a>
 */
class _128_Longest_Consecutive_Sequence {
    public int longestConsecutive(int[] nums) {
        var map = new HashSet<Integer>();
        for (int num : nums) {
            map.add(num);
        }
        var max = 0;
        for (var v : map) {
            if (!map.contains(v - 1)) {
                var next = v + 1;
                while (map.contains(next)) {
                    next++;
                }
                max = Math.max(max, next - v);
            }
        }
        return max;
    }

    @Test
    void examples() {
        assertEquals(4, longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        assertEquals(9, longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    @Test
    void advance() {
        assertEquals(0, longestConsecutive(new int[]{}));
        assertEquals(1, longestConsecutive(new int[]{0}));
        assertEquals(3, longestConsecutive(new int[]{1, 0, -1}));
    }
}
