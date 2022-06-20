import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/number-of-1-bits/">191. Number of 1 Bits</a>
 */
public class _191_Number_of_1_Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            result += n & 1;
            n >>>= 1;
        }
        return result;
    }

    @Test
    void examples() {
        assertEquals(3, hammingWeight((int) Long.parseLong("00000000000000000000000000001011", 2)));
        assertEquals(1, hammingWeight((int) Long.parseLong("00000000000000000000000010000000", 2)));
        assertEquals(31, hammingWeight((int) Long.parseLong("11111111111111111111111111111101", 2)));
    }
}
