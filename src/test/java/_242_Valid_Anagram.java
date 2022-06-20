import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/valid-anagram/">242. Valid Anagram</a>
 */
class _242_Valid_Anagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        var map = new int[26];
        for (var c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (var c : t.toCharArray()) {
            if (--map[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    void examples() {
        assertTrue(isAnagram("anagram", "nagaram"));
        assertFalse(isAnagram("rat", "car"));
    }
}
