import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/rotate-image/">48. Rotate Image</a>
 */
class _48_Rotate_Image {
    int getAndSet(int[][] matrix, int x, int y, int value) {
        int temp = matrix[x][y];
        matrix[x][y] = value;
        return temp;
    }

    void rotate(int[][] matrix) {
        int temp;
        for (int x = 0; x < matrix.length - 1; x++) {
            for (int y = x; y < matrix.length - 1 - x; y++) {
                temp = getAndSet(matrix, y, matrix[x].length - 1 - x, matrix[x][y]);
                temp = getAndSet(matrix, matrix.length - 1 - x, matrix[x].length - 1 - y, temp);
                temp = getAndSet(matrix, matrix.length - 1 - y, x, temp);
                matrix[x][y] = temp;
            }
        }
    }

    void rotate4Vars(int[][] matrix) {
        int LU, RU, LD, RD;
        for (int x = 0; x < matrix.length - 1; x++) {
            for (int y = x; y < matrix.length - 1 - x; y++) {
                LU = matrix[x][y];
                RU = matrix[y][matrix[x].length - 1 - x];
                RD = matrix[matrix.length - 1 - x][matrix[x].length - 1 - y];
                LD = matrix[matrix.length - 1 - y][x];

                matrix[x][y] = LD;
                matrix[y][matrix[x].length - 1 - x] = LU;
                matrix[matrix.length - 1 - x][matrix[x].length - 1 - y] = RU;
                matrix[matrix.length - 1 - y][x] = RD;
            }
        }
    }

    void rotate2Vars(int[][] matrix) {
        int t0, t1;
        for (int x = 0; x < matrix.length - 1; x++) {
            for (int y = x; y < matrix.length - 1 - x; y++) {
                t0 = matrix[y][matrix[x].length - 1 - x];
                matrix[y][matrix[x].length - 1 - x] = matrix[x][y];

                t1 = matrix[matrix.length - 1 - x][matrix[x].length - 1 - y];
                matrix[matrix.length - 1 - x][matrix[x].length - 1 - y] = t0;

                t0 = matrix[matrix.length - 1 - y][x];
                matrix[matrix.length - 1 - y][x] = t1;

                matrix[x][y] = t0;
            }
        }
    }

    @Test
    void examples() {
        int[][] input;
        input = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(input);
        assertTrue(CommonUtils.compare(new int[][]{
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        }, input));

        input = new int[][]{
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        rotate(input);
        assertTrue(CommonUtils.compare(new int[][]{
                {15, 13, 2, 5},
                {14, 3, 4, 1},
                {12, 6, 8, 9},
                {16, 7, 10, 11}
        }, input));
    }
}
