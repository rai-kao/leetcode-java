import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/minimum-window-substring/">76. Minimum Window Substring</a>
 */
class _76_Minimum_Window_Substring {
    String longInput0;
    String longInput1;
    String longInput2;

    String longInput3;
    String longInput4;
    String longInput5;

    boolean contains(HashMap<Character, Integer> a, HashMap<Character, Integer> b) {
        for (var key : a.keySet()) {
            if (!b.containsKey(key)) {
                return false;
            }

            if (b.get(key) - a.get(key) < 0) {
                return false;
            }
        }
        return true;
    }

    String minWindowMine(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        if (t.length() == 1) {
            return s.contains(t) ? t : "";
        }

        var target = new HashMap<Character, Integer>();
        for (var c : t.toCharArray()) {
            if (!target.containsKey(c)) {
                target.put(c, 1);
            } else {
                target.put(c, target.get(c) + 1);
            }
        }

        var source = new HashMap<Character, Integer>();
        var left = 0;
        var right = Integer.MAX_VALUE;
        var min = Integer.MAX_VALUE;
        var retL = 0;
        var retR = Integer.MAX_VALUE;
        for (var i = 0; 0 <= i && i < s.length(); i++) {
            if (!target.containsKey(s.charAt(i))) {
                continue;
            }

            if (!source.containsKey(s.charAt(i))) {
                source.put(s.charAt(i), 1);
            } else {
                source.put(s.charAt(i), source.get(s.charAt(i)) + 1);
            }

            if (contains(target, source)) {
                right = i;
                for (int j = left; j < right; j++) {
                    if (!target.containsKey(s.charAt(j))) {
                        continue;
                    }
                    source.put(s.charAt(j), source.get(s.charAt(j)) - 1);
                    if (!contains(target, source)) {
                        source.put(s.charAt(j), source.get(s.charAt(j)) + 1);
                        left = j;
                        if (right - left < min) {
                            retL = left;
                            retR = right;
                            min = right - left;
                        }
                        break;
                    }
                }
            }
        }

        return retR >= s.length() ? "" : String.copyValueOf(s.toCharArray(), retL, retR - retL + 1);
    }

    String minWindowStandard(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] > 0) counter--;
            map[c1]--;
            end++;
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                final char c2 = s.charAt(start);
                map[c2]++;
                if (map[c2] > 0) counter++;
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    String minWindowFast(String s, String t) {
        int[] need = new int[123];
        int miss = t.length();
        char[] have = s.toCharArray();
        for (char c : t.toCharArray()) need[c]++;
        int i = 0, j = 0, l = 0, r = 0;

        while (r < s.length()) {
            if (need[have[r]] > 0) miss--;
            need[have[r]]--;
            r++;

            while (miss == 0) {
                if (j == 0 || (r - l) < (j - i)) {
                    j = r;
                    i = l;
                }
                need[have[l]]++;
                if (need[have[l]] > 0) miss++;
                l++;
            }
        }
        return s.substring(i, j);
    }

    String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        int[] map = new int[123]; // ASCII code z = 122
        var require = t.length();
        var template = t.toCharArray();
        for (var c : template) {
            map[c]++;
        }

        int start = 0, end = 0, min = Integer.MAX_VALUE, l = 0, r = 0;
        var source = s.toCharArray();
        while (end < source.length) {
            if (map[source[end]] > 0) {
                require--;
            }
            map[source[end]]--;
            while (require == 0) {
                map[source[start]]++;
                if (map[source[start]] > 0) {
                    require++;
                    if (min > end - start) {
                        min = end - start;
                        l = start;
                        r = end;
                    }
                }
                start++;
            }
            end++;
        }

        return min < Integer.MAX_VALUE ? s.substring(l, r + 1) : "";
    }

    void initialize() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("_76_Minimum_Window_Substring.properties");
        properties.load(inputStream);
        longInput0 = properties.getProperty("advance0");
        longInput1 = properties.getProperty("advance1");
        longInput2 = properties.getProperty("advance2");

        longInput3 = properties.getProperty("advance3");
        longInput4 = properties.getProperty("advance4");
        longInput5 = properties.getProperty("advance5");
    }

    @Test
    void examples() {
        assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
        assertEquals("a", minWindow("a", "a"));
        assertEquals("aba", minWindow("abac", "aba"));
        assertEquals("", minWindow("a", "aa"));
    }

    @Test
    void advance() {
        assertDoesNotThrow(this::initialize);
        assertEquals("a", minWindow("ab", "a"));
        assertEquals("aec", minWindow("cabefgecdaecf", "cae"));
        assertEquals("", minWindow("babbBba", "Abbb"));
        assertEquals("CBA", minWindow("ADOBECODECBANC", "ABC"));
        assertEquals(longInput2, minWindow(longInput0, longInput1));
        assertEquals(longInput5, minWindow(longInput3, longInput4));
    }
}
