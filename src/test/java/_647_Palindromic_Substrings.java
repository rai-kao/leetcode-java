import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/palindromic-substrings/">647. Palindromic Substrings</a>
 */
class _647_Palindromic_Substrings {
    public int countSubstrings(String s) {
        if (s.length() <= 1) {
            return 1;
        }

        var str = s.toCharArray();
        var count = new int[1];
        for (int i = 0; i < str.length; i++) {
            extend(str, i, i, count);
            extend(str, i, i + 1, count);
        }
        return count[0];
    }

    void extend(char[] str, int l, int r, int[] count) {
        while (l >= 0 && r < str.length && str[l] == str[r]) {
            count[0]++;
            l--;
            r++;
        }
    }

    @Test
    void examples() {
        assertEquals(3, countSubstrings("abc"));
        assertEquals(6, countSubstrings("aaa"));
    }

    @Test
    void advance() {
        assertEquals(4, countSubstrings("aba"));
    }
}
