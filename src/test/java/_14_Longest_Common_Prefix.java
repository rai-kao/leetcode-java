import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
class _14_Longest_Common_Prefix {
    String longestCommonPrefix(String[] strs) {
        var longest = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            var cur = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || cur != strs[j].charAt(i)) {
                    return longest.toString();
                }
            }
            longest.append(cur);
        }
        return longest.toString();
    }

    @Test
    void examples() {
        assertEquals("fl", longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        assertEquals("", longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    @Test
    void advance() {
        // StringIndexOutOfBoundsException
        assertEquals("flow", longestCommonPrefix(new String[]{"flower", "flow", "flowht"}));
    }
}
