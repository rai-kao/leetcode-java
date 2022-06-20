import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/course-schedule/">207. Course Schedule</a>
 */
class _207_Course_Schedule {
    boolean hasCycle(List<List<Integer>> courses, int[] visited, int course) {
        if (visited[course] == 1) {
            return false;
        }
        if (visited[course] == -1) {
            return true;
        }
        visited[course] = -1;
        for (var adj : courses.get(course)) {
            if (hasCycle(courses, visited, adj)) {
                return true;
            }
        }
        visited[course] = 1;
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        var graph = new ArrayList<List<Integer>>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(i, new ArrayList<>());
        }
        var visited = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }

        for (var node : graph) {
            for (var adj : node) {
                if (hasCycle(graph, visited, adj)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        var incoming = new int[numCourses];
        var graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (var edge : prerequisites) {
            incoming[edge[1]]++;
            graph.get(edge[0]).add(edge[1]);
        }
        var queue = new LinkedList<Integer>();
        for (var i = 0; i < incoming.length; i++) {
            if (incoming[i] == 0) {
                queue.add(i);
            }
        }

        var edges = prerequisites.length;
        while (!queue.isEmpty()) {
            var course = queue.poll();
            for (var adj : graph.get(course)) {
                edges--;
                incoming[adj]--;
                if (incoming[adj] == 0) {
                    queue.add(adj);
                }
            }
        }
        return edges == 0;
    }

    @Test
    void examples() {
        assertTrue(canFinish(2, new int[][]{{1, 0}}));
        assertFalse(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    @Test
    void advance() {
        assertTrue(canFinish(1, new int[][]{}));
        assertFalse(canFinish(20, new int[][]{{0, 10}, {3, 18}, {5, 5}, {6, 11}, {11, 14}, {13, 1}, {15, 1}, {17, 4}}));
        assertTrue(canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
        assertTrue(canFinish(100, new int[][]{
                {1, 0}, {2, 0}, {2, 1}, {3, 1}, {3, 2}, {4, 2}, {4, 3}, {5, 3}, {5, 4}, {6, 4}, {6, 5}, {7, 5}, {7, 6}, {8, 6}, {8, 7}, {9, 7}, {9, 8}, {10, 8}, {10, 9}, {11, 9}, {11, 10}, {12, 10}, {12, 11}, {13, 11}, {13, 12}, {14, 12}, {14, 13}, {15, 13}, {15, 14}, {16, 14}, {16, 15}, {17, 15}, {17, 16}, {18, 16}, {18, 17}, {19, 17}, {19, 18}, {20, 18}, {20, 19}, {21, 19}, {21, 20}, {22, 20}, {22, 21}, {23, 21}, {23, 22}, {24, 22}, {24, 23}, {25, 23}, {25, 24}, {26, 24}, {26, 25}, {27, 25}, {27, 26}, {28, 26}, {28, 27}, {29, 27}, {29, 28}, {30, 28}, {30, 29}, {31, 29}, {31, 30}, {32, 30}, {32, 31}, {33, 31}, {33, 32}, {34, 32}, {34, 33}, {35, 33}, {35, 34}, {36, 34}, {36, 35}, {37, 35}, {37, 36}, {38, 36}, {38, 37}, {39, 37}, {39, 38}, {40, 38}, {40, 39}, {41, 39}, {41, 40}, {42, 40}, {42, 41}, {43, 41}, {43, 42}, {44, 42}, {44, 43}, {45, 43}, {45, 44}, {46, 44}, {46, 45}, {47, 45}, {47, 46}, {48, 46}, {48, 47}, {49, 47}, {49, 48}, {50, 48}, {50, 49}, {51, 49}, {51, 50}, {52, 50}, {52, 51}, {53, 51}, {53, 52}, {54, 52}, {54, 53}, {55, 53}, {55, 54}, {56, 54}, {56, 55}, {57, 55}, {57, 56}, {58, 56}, {58, 57}, {59, 57}, {59, 58}, {60, 58}, {60, 59}, {61, 59}, {61, 60}, {62, 60}, {62, 61}, {63, 61}, {63, 62}, {64, 62}, {64, 63}, {65, 63}, {65, 64}, {66, 64}, {66, 65}, {67, 65}, {67, 66}, {68, 66}, {68, 67}, {69, 67}, {69, 68}, {70, 68}, {70, 69}, {71, 69}, {71, 70}, {72, 70}, {72, 71}, {73, 71}, {73, 72}, {74, 72}, {74, 73}, {75, 73}, {75, 74}, {76, 74}, {76, 75}, {77, 75}, {77, 76}, {78, 76}, {78, 77}, {79, 77}, {79, 78}, {80, 78}, {80, 79}, {81, 79}, {81, 80}, {82, 80}, {82, 81}, {83, 81}, {83, 82}, {84, 82}, {84, 83}, {85, 83}, {85, 84}, {86, 84}, {86, 85}, {87, 85}, {87, 86}, {88, 86}, {88, 87}, {89, 87}, {89, 88}, {90, 88}, {90, 89}, {91, 89}, {91, 90}, {92, 90}, {92, 91}, {93, 91}, {93, 92}, {94, 92}, {94, 93}, {95, 93}, {95, 94}, {96, 94}, {96, 95}, {97, 95}, {97, 96}, {98, 96}, {98, 97}, {99, 97}}));
    }
}
