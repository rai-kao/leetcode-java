import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/implement-strstr/
 */
class _28_Implement_strStr {
    int strStr(String haystack, String needle) {
        if (needle.length() <= 0) {
            return 0;
        }

        int match = 0;
        var first = new LinkedList<Integer>();
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(match)) {
                match++;
                if (match == 1) {
                    first.push(i);
                }
                if (match >= needle.length()) {
                    return i - (needle.length() - 1);
                }
            } else {
                match = 0;
                if (first.size() > 0) {
                    i = first.pop();
                }
            }
        }

        return -1;
    }

    @Test
    void examples() {
        assertEquals(2, strStr("hello", "ll"));
        assertEquals(-1, strStr("aaaaa", "bba"));
    }

    @Test
    void advance() {
        assertEquals(0, strStr("a", "a"));
        assertEquals(0, strStr("aaa", "aaa"));
        assertEquals(-1, strStr("aaa", "aaaa"));
        assertEquals(4, strStr("mississippi", "issip"));
    }
}
