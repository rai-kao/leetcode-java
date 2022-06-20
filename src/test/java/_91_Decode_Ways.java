import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/decode-ways/">91. Decode Ways</a>
 */
class _91_Decode_Ways {
    int numDecodings(String s) {
        var f0 = 0;
        var f1 = 1;
        var fn = 0;
        var c0 = '0';
        for (var i = 0; i < s.length(); i++) {
            var cn = s.charAt(i);
            fn = 0;
            if ('1' <= cn && cn <= '9') {
                fn += f1;
            }
            if ((c0 == '1' && '0' <= cn && cn <= '9') ||
                    (c0 == '2' && '0' <= cn && cn <= '6')) {
                fn += f0;
            }
            c0 = cn;
            f0 = f1;
            f1 = fn;
        }
        return fn;
    }

    @Test
    void examples() {
        assertEquals(2, numDecodings("12"));
        assertEquals(3, numDecodings("226"));
        assertEquals(0, numDecodings("06"));
    }

    @Test
    void advance() {
        assertEquals(1836311903, numDecodings("111111111111111111111111111111111111111111111"));
        assertEquals(1, numDecodings("10"));
        assertEquals(1, numDecodings("2101"));
        assertEquals(2, numDecodings("22101"));
        assertEquals(0, numDecodings("10011"));
        assertEquals(4, numDecodings("2611055971756562"));
    }
}
