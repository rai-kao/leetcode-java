import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/roman-to-integer/
 */
class _13_Roman_to_Integer {
    int romanToInt(String s) {
        var roman = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        var subtraction = new HashMap<String, Integer>() {{
            put("IV", 4);
            put("IX", 9);
            put("XL", 40);
            put("XC", 90);
            put("CD", 400);
            put("CM", 900);
        }};

        int translation = 0;
        var sub = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            var cur = s.charAt(i);
            var numeral = roman.get(cur);
            sub.append(cur);
            if (sub.length() == 2) {
                var replacement = sub.toString();
                if (subtraction.containsKey(replacement)) {
                    numeral = subtraction.get(sub.toString());
                    translation -= roman.get(sub.charAt(0));
                    sub.setLength(0);
                } else {
                    sub.deleteCharAt(0);
                }
            }
            translation += numeral;
        }

        return translation;
    }

    @Test
    void examples() {
        assertEquals(3, romanToInt("III"));
        assertEquals(58, romanToInt("LVIII"));
        assertEquals(1994, romanToInt("MCMXCIV"));
    }
}
