import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/meeting-rooms-ii/">253. Meeting Rooms II</a>
 * @see <a href="https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/253.%20Meeting%20Rooms%20II.md">Algorithm-and-Leetcode/leetcode/253. Meeting Rooms II</a>
 * @see <a href="https://www.interviewbit.com/problems/meeting-rooms/">Meeting rooms</a>
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0, 30],[5, 10],[15, 20]]
 * <p>
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * <p>
 * Output: 1
 */
class _253_Meeting_Rooms_II {
    public int minMeetingRoomsFast(int[][] intervals) {
        if (intervals.length <= 1) return intervals.length;
        int result = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        pq.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pq.peek()[1]) {
                pq.poll();
            }
            pq.offer(intervals[i]);
            result = Math.max(result, pq.size());
        }
        return result;
    }

    public int minMeetingRooms(ArrayList<ArrayList<Integer>> A) {
        if (A.size() <= 1) {
            return A.size();
        }
        int result = 1;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));
        A.sort((a, b) -> !a.get(0).equals(b.get(0)) ? a.get(0) - b.get(0) : a.get(1) - b.get(1));
        pq.offer(A.get(0));
        for (int i = 1; i < A.size(); i++) {
            if (A.get(i).get(0) >= pq.peek().get(1)) {
                pq.poll();
            }
            pq.offer(A.get(i));
            result = Math.max(result, pq.size());
        }
        return result;
    }

    public int minMeetingRoomsPQ(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int room = 0;
        for (int[] interval : intervals) {
            pq.offer(interval[1]);
            if (interval[0] < pq.peek()) room++;
            else {
                pq.poll();
            }
        }
        return room;
    }

    public int minMeetingRooms(int[][] intervals) {
        var start = new int[intervals.length];
        var end = new int[intervals.length];
        for (var i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        var max = 0;
        var s = 0;
        var e = 0;
        var room = 0;
        while (s < start.length && e < end.length) {
            if (end[e] > start[s]) {
                s++;
                room++;
                max = Math.max(max, room);
            } else {
                e++;
                room--;
            }
        }
        return max;
    }

    @Test
    void examples() {
        assertEquals(2, minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        assertEquals(1, minMeetingRooms(new int[][]{{7, 10}, {2, 4}}));
    }

    @Test
    void advance() {
        int[][] input;

        input = new int[][]{{1, 4}, {2, 5}, {10, 12}, {5, 9}, {5, 12}};
        assertEquals(minMeetingRoomsFast(input), minMeetingRooms(input));

        input = new int[][]{{3, 27}, {5, 21}, {2, 8}, {4, 16}, {15, 21}, {10, 20}, {17, 29}, {23, 25}};
        assertEquals(5, minMeetingRoomsPQ(input));

        input = new int[][]{
                {57188, 88859},
                {13405, 76916},
                {5182, 82309},
                {8295, 90374},
                {3743, 21328},
                {4255, 66894},
                {79623, 87153},
                {11977, 13230},
                {8198, 31248},
                {77598, 96274},
                {27304, 81731},
                {15400, 70826},
                {22335, 53090},
                {983, 62922},
                {9888, 80129},
                {32770, 49164},
                {33435, 71204},
                {32503, 89248},
                {23031, 23782},
                {28329, 85707},
                {17561, 47786},
                {71226, 98612},
                {52459, 97416},
                {38269, 77063},
                {21957, 46006},
                {64006, 83229},
                {237, 52864},
                {18086, 36388},
                {77576, 79380},
                {45654, 80340},
                {9702, 93046},
                {34051, 79619},
                {59164, 97236},
                {80674, 89177},
                {43226, 79288},
                {19716, 45737},
                {16927, 19120},
                {22571, 71096},
                {44728, 46110},
                {5270, 93960},
                {3403, 89287},
                {59917, 83426},
                {43823, 82984},
                {51840, 88148},
                {9087, 22913},
                {8607, 13094},
                {26928, 87985},
                {4611, 15653},
                {31510, 91885},
                {81944, 91853},
                {15062, 45403},
                {58425, 76948},
                {43314, 58089},
                {40178, 41445},
                {17051, 94749},
                {1676, 7391},
                {3317, 11658},
                {16988, 56312},
                {9511, 25926},
                {58567, 94253},
                {8340, 76464},
                {51076, 82937},
                {17380, 91293},
                {28209, 64495},
                {43865, 75661},
                {34022, 50492},
                {49434, 88793},
                {51884, 62749},
                {40628, 82649},
                {50000, 53881},
                {84216, 87681},
                {34390, 70474},
                {55648, 57767},
                {10332, 54100},
                {71068, 90020}};
        assertEquals(minMeetingRoomsFast(input), minMeetingRooms(input));

        var A = new ArrayList<ArrayList<Integer>>() {{
            add(new ArrayList<>() {{
                add(57188);
                add(88859);
            }});
            add(new ArrayList<>() {{
                add(13405);
                add(76916);
            }});
            add(new ArrayList<>() {{
                add(5182);
                add(82309);
            }});
            add(new ArrayList<>() {{
                add(8295);
                add(90374);
            }});
            add(new ArrayList<>() {{
                add(3743);
                add(21328);
            }});
            add(new ArrayList<>() {{
                add(4255);
                add(66894);
            }});
            add(new ArrayList<>() {{
                add(79623);
                add(87153);
            }});
            add(new ArrayList<>() {{
                add(11977);
                add(13230);
            }});
            add(new ArrayList<>() {{
                add(8198);
                add(31248);
            }});
            add(new ArrayList<>() {{
                add(77598);
                add(96274);
            }});
            add(new ArrayList<>() {{
                add(27304);
                add(81731);
            }});
            add(new ArrayList<>() {{
                add(15400);
                add(70826);
            }});
            add(new ArrayList<>() {{
                add(22335);
                add(53090);
            }});
            add(new ArrayList<>() {{
                add(983);
                add(62922);
            }});
            add(new ArrayList<>() {{
                add(9888);
                add(80129);
            }});
            add(new ArrayList<>() {{
                add(32770);
                add(49164);
            }});
            add(new ArrayList<>() {{
                add(33435);
                add(71204);
            }});
            add(new ArrayList<>() {{
                add(32503);
                add(89248);
            }});
            add(new ArrayList<>() {{
                add(23031);
                add(23782);
            }});
            add(new ArrayList<>() {{
                add(28329);
                add(85707);
            }});
            add(new ArrayList<>() {{
                add(17561);
                add(47786);
            }});
            add(new ArrayList<>() {{
                add(71226);
                add(98612);
            }});
            add(new ArrayList<>() {{
                add(52459);
                add(97416);
            }});
            add(new ArrayList<>() {{
                add(38269);
                add(77063);
            }});
            add(new ArrayList<>() {{
                add(21957);
                add(46006);
            }});
            add(new ArrayList<>() {{
                add(64006);
                add(83229);
            }});
            add(new ArrayList<>() {{
                add(237);
                add(52864);
            }});
            add(new ArrayList<>() {{
                add(18086);
                add(36388);
            }});
            add(new ArrayList<>() {{
                add(77576);
                add(79380);
            }});
            add(new ArrayList<>() {{
                add(45654);
                add(80340);
            }});
            add(new ArrayList<>() {{
                add(9702);
                add(93046);
            }});
            add(new ArrayList<>() {{
                add(34051);
                add(79619);
            }});
            add(new ArrayList<>() {{
                add(59164);
                add(97236);
            }});
            add(new ArrayList<>() {{
                add(80674);
                add(89177);
            }});
            add(new ArrayList<>() {{
                add(43226);
                add(79288);
            }});
            add(new ArrayList<>() {{
                add(19716);
                add(45737);
            }});
            add(new ArrayList<>() {{
                add(16927);
                add(19120);
            }});
            add(new ArrayList<>() {{
                add(22571);
                add(71096);
            }});
            add(new ArrayList<>() {{
                add(44728);
                add(46110);
            }});
            add(new ArrayList<>() {{
                add(5270);
                add(93960);
            }});
            add(new ArrayList<>() {{
                add(3403);
                add(89287);
            }});
            add(new ArrayList<>() {{
                add(59917);
                add(83426);
            }});
            add(new ArrayList<>() {{
                add(43823);
                add(82984);
            }});
            add(new ArrayList<>() {{
                add(51840);
                add(88148);
            }});
            add(new ArrayList<>() {{
                add(9087);
                add(22913);
            }});
            add(new ArrayList<>() {{
                add(8607);
                add(13094);
            }});
            add(new ArrayList<>() {{
                add(26928);
                add(87985);
            }});
            add(new ArrayList<>() {{
                add(4611);
                add(15653);
            }});
            add(new ArrayList<>() {{
                add(31510);
                add(91885);
            }});
            add(new ArrayList<>() {{
                add(81944);
                add(91853);
            }});
            add(new ArrayList<>() {{
                add(15062);
                add(45403);
            }});
            add(new ArrayList<>() {{
                add(58425);
                add(76948);
            }});
            add(new ArrayList<>() {{
                add(43314);
                add(58089);
            }});
            add(new ArrayList<>() {{
                add(40178);
                add(41445);
            }});
            add(new ArrayList<>() {{
                add(17051);
                add(94749);
            }});
            add(new ArrayList<>() {{
                add(1676);
                add(7391);
            }});
            add(new ArrayList<>() {{
                add(3317);
                add(11658);
            }});
            add(new ArrayList<>() {{
                add(16988);
                add(56312);
            }});
            add(new ArrayList<>() {{
                add(9511);
                add(25926);
            }});
            add(new ArrayList<>() {{
                add(58567);
                add(94253);
            }});
            add(new ArrayList<>() {{
                add(8340);
                add(76464);
            }});
            add(new ArrayList<>() {{
                add(51076);
                add(82937);
            }});
            add(new ArrayList<>() {{
                add(17380);
                add(91293);
            }});
            add(new ArrayList<>() {{
                add(28209);
                add(64495);
            }});
            add(new ArrayList<>() {{
                add(43865);
                add(75661);
            }});
            add(new ArrayList<>() {{
                add(34022);
                add(50492);
            }});
            add(new ArrayList<>() {{
                add(49434);
                add(88793);
            }});
            add(new ArrayList<>() {{
                add(51884);
                add(62749);
            }});
            add(new ArrayList<>() {{
                add(40628);
                add(82649);
            }});
            add(new ArrayList<>() {{
                add(50000);
                add(53881);
            }});
            add(new ArrayList<>() {{
                add(84216);
                add(87681);
            }});
            add(new ArrayList<>() {{
                add(34390);
                add(70474);
            }});
            add(new ArrayList<>() {{
                add(55648);
                add(57767);
            }});
            add(new ArrayList<>() {{
                add(10332);
                add(54100);
            }});
            add(new ArrayList<>() {{
                add(71068);
                add(90020);
            }});
        }};
        assertEquals(minMeetingRoomsFast(input), minMeetingRooms(A));
    }
}
