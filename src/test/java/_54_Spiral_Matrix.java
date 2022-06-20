import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/spiral-matrix/">54. Spiral Matrix</a>
 */
class _54_Spiral_Matrix {
    List<Integer> spiralOrder(int[][] matrix) {
        var ret = new ArrayList<Integer>();
        var rowStart = 0;
        var rowEnd = matrix.length - 1;
        var colStart = 0;
        var colEnd = matrix[0].length - 1;
        var step = 0;
        while (ret.size() < matrix.length * matrix[0].length) {
            switch (step % 4) {
                case 0 -> {
                    for (int i = colStart; i <= colEnd; i++) {
                        ret.add(matrix[rowStart][i]);
                    }
                    rowStart++;
                }
                case 1 -> {
                    for (int j = rowStart; j <= rowEnd; j++) {
                        ret.add(matrix[j][colEnd]);
                    }
                    colEnd--;
                }
                case 2 -> {
                    for (int k = colEnd; k >= colStart; k--) {
                        ret.add(matrix[rowEnd][k]);
                    }
                    rowEnd--;
                }
                case 3 -> {
                    for (int l = rowEnd; l >= rowStart; l--) {
                        ret.add(matrix[l][colStart]);
                    }
                    colStart++;
                }
            }
            step++;
        }
        return ret;
    }

    @Test
    void examples() {
        assertEquals(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5),
                spiralOrder(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                }));

        assertEquals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
                spiralOrder(new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12}
                }));

        assertEquals(List.of(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10),
                spiralOrder(new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}
                }));
    }
}
