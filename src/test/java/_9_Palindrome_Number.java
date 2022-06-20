import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/palindrome-number/
 * https://www.geeksforgeeks.org/c-program-check-given-string-palindrome/
 */
class _9_Palindrome_Number {
    boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        var input = Integer.toString(x).toCharArray();
        for (int l = 0, r = input.length - 1; l < r; l++, r--) {
            if (input[l] != input[r]) {
                return false;
            }
        }

        return true;
    }

    @Test
    void examples() {
        assertEquals(true, isPalindrome(121));
        assertEquals(false, isPalindrome(-121));
        assertEquals(false, isPalindrome(10));
    }
}
