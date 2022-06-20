import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/meeting-rooms/">252. Meeting Rooms</a>
 * @see <a href="https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/252.%20Meeting%20Rooms.md">Algorithm-and-Leetcode/leetcode/252. Meeting Rooms</a>
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,30],[5,10],[15,20]]
 * <p>
 * Output: false
 * <p>
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * <p>
 * Output: true
 * <p>
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
class _252_Meeting_Rooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }

        return true;
    }

    @Test
    void examples() {
        assertFalse(canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        assertTrue(canAttendMeetings(new int[][]{{7, 10}, {2, 4}}));
    }
}
