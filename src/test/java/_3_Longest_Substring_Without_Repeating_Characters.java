import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 */
class _3_Longest_Substring_Without_Repeating_Characters {
    int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        StringBuilder subStr = new StringBuilder();
        int maxLength = 0;
        for (char c : s.toCharArray()) {
            String current = String.valueOf(c);
            if (subStr.toString().contains(current)) {
                subStr = new StringBuilder(subStr.substring(subStr.indexOf(current) + 1));
            }
            subStr.append(c);
            maxLength = Math.max(subStr.length(), maxLength);
        }

        return maxLength;
    }

    @Test
    void examples() {
        assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    void advance() {
        assertEquals(1, lengthOfLongestSubstring(" "));
        assertEquals(3, lengthOfLongestSubstring("dvdf"));
    }
}
