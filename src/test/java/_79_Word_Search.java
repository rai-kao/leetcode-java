import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href = "https://leetcode.com/problems/word-search/">79. Word Search</a>
 */
class _79_Word_Search {
    boolean next(char[][] board, String word, int row, int col, int length) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
            return false;
        }

        if (length == word.length() - 1) {
            return word.charAt(length) == board[row][col];
        }

        if (word.charAt(length) != board[row][col]) {
            return false;
        }

        var current = board[row][col];
        board[row][col] = '*';

        var ret = next(board, word, row + 1, col, length + 1) ||
                next(board, word, row - 1, col, length + 1) ||
                next(board, word, row, col + 1, length + 1) ||
                next(board, word, row, col - 1, length + 1);
        board[row][col] = current;
        return ret;
    }

    boolean exist(char[][] board, String word) {
        for (var m = 0; m < board.length; m++) {
            for (var n = 0; n < board[m].length; n++) {
                if (next(board, word, m, n, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    void examples() {
        assertEquals(true, exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED"));
        assertEquals(true, exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "SEE"));
        assertEquals(false, exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCB"));
    }

    @Test
    void advance() {
        assertEquals(true, exist(new char[][]{
                {'a'}
        }, "a"));
        assertEquals(true, exist(new char[][]{
                {'a', 'a'}
        }, "aa"));
        assertEquals(false, exist(new char[][]{
                {'A', 'A', 'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A', 'A', 'A'}
        }, "AAAAAAAAAAAAAAB"));
    }
}
