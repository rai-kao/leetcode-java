import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/reverse-bits/">190. Reverse Bits</a>
 */
class _190_Reverse_Bits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result += n & 1;
            n >>>= 1;
        }
        return result;
    }

    @Test
    void examples() {
        assertEquals((int) Long.parseLong("00111001011110000010100101000000", 2),
                reverseBits((int) Long.parseLong("00000010100101000001111010011100", 2)));
        assertEquals((int) Long.parseLong("10111111111111111111111111111111", 2),
                reverseBits((int) Long.parseLong("11111111111111111111111111111101", 2)));
    }
}
