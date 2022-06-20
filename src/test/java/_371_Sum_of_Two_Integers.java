import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/sum-of-two-integers/">371. Sum of Two Integers</a>
 */
class _371_Sum_of_Two_Integers {
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }

    @Test
    void examples() {
        assertEquals(3, getSum(1, 2));
        assertEquals(5, getSum(2, 3));
    }
}
