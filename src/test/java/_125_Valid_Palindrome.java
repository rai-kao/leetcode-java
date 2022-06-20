import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/valid-palindrome/">125. Valid Palindrome</a>
 */
class _125_Valid_Palindrome {
    public boolean isPalindrome(String s) {
        var l = 0;
        var r = s.length() - 1;
        while (l < r) {
            char lc = ' ';
            while (l < s.length()) {
                if (Character.isLetterOrDigit(s.charAt(l))) {
                    lc = Character.toLowerCase(s.charAt(l));
                    break;
                }
                l++;
            }
            char rc = ' ';
            while (r >= 0) {
                if (Character.isLetterOrDigit(s.charAt(r))) {
                    rc = Character.toLowerCase(s.charAt(r));
                    break;
                }
                r--;
            }
            if (lc != rc) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    @Test
    void examples() {
        assertEquals(true, isPalindrome("A man, a plan, a canal: Panama"));
        assertEquals(false, isPalindrome("race a car"));
        assertEquals(true, isPalindrome(" "));
    }

    @Test
    void advance() {
        assertEquals(true, isPalindrome(".,"));
        assertEquals(false, isPalindrome("0P"));
    }
}
