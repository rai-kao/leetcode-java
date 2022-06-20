import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/longest-repeating-character-replacement/">424. Longest Repeating Character Replacement</a>
 */
class _424_Longest_Repeating_Character_Replacement {
    public int characterReplacementNormal(String s, int k) {
        var map = new int[26];
        var input = s.toCharArray();
        var l = 0;
        var len = 0;
        var max = 0;
        for (var r = 0; r < input.length; r++) {
            map[input[r] - 'A']++;
            len++;
            if (!isValid(map, k)) {
                map[input[l++] - 'A']--;
                len--;
            }
            max = Math.max(max, len);
        }
        return max;
    }

    boolean isValid(int[] map, int k) {
        var bucket = Arrays.copyOf(map, map.length);
        Arrays.sort(bucket);
        var sum = 0;
        for (var i = bucket.length - 2; i >= 0; i--) {
            sum += bucket[i];
        }
        return sum <= k;
    }

    public int characterReplacement(String s, int k) {
        var map = new int[26];
        var input = s.toCharArray();
        var l = 0;
        var r = 0;
        var len = 0;
        for (r = 0; r < input.length; r++) {
            len = Math.max(len, ++map[input[r] - 'A']);
            if (len + k <= r - l) {
                map[input[l++] - 'A']--;
            }
        }
        return r - l;
    }

    @Test
    void examples() {
        assertEquals(4, characterReplacement("ABAB", 2));
        assertEquals(4, characterReplacement("AABABBA", 1));
        assertEquals(1, characterReplacement("A", 1));
        assertEquals(2, characterReplacement("AABABBA", 0));

        assertEquals(5, characterReplacement("AABABBA", 2));
        assertEquals(3, characterReplacement("ABCDEFG", 2));
    }

    @Test
    void advance() {
        assertEquals(2, characterReplacement("ABAA", 0));
        assertEquals(7, characterReplacement("KRSCDCSONAJNHLBMDQGIFCPEKPOHQIHLTDIQGEKLRLCQNBOHNDQGHJPNDQPERNFSSSRDEQLFPCCCARFMDLHADJADAGNNSBNCJQOF", 4));
    }
}
