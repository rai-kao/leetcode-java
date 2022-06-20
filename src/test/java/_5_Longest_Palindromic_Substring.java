import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm
 * https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-4/
 */
class _5_Longest_Palindromic_Substring {
    String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        var sp = ("|" + String.join("|", s.split("")) + "|").toCharArray();

        var center = 0;
        var radius = 0;
        var length = sp.length;
        var palindromeRadius = new int[length];
        Arrays.fill(palindromeRadius, 0);
        var maxRadius = 0;
        var maxRadiusCenter = 0;
        while (center < length) {
            while ((center - (radius + 1)) >= 0 &&
                    (center + (radius + 1)) < length &&
                    sp[center - (radius + 1)] == sp[center + (radius + 1)]) {
                radius++;
            }

            palindromeRadius[center] = radius;
            if (radius > maxRadius) {
                maxRadius = radius;
                maxRadiusCenter = center;
            }
            var oldCenter = center;
            var oldRadius = radius;
            center++;
            radius = 0;
            while (center <= oldCenter + oldRadius) {
                var mirroredCenter = oldCenter - (center - oldCenter);
                var maxMirroredRadius = oldCenter + oldRadius - center;
                if (palindromeRadius[mirroredCenter] < maxMirroredRadius) {
                    palindromeRadius[center] = palindromeRadius[mirroredCenter];
                    center++;
                } else if (palindromeRadius[mirroredCenter] > maxMirroredRadius) {
                    palindromeRadius[center] = maxMirroredRadius;
                    center++;
                } else {
                    radius = maxMirroredRadius;
                    break;
                }
            }
        }
        return String.copyValueOf(Arrays.copyOfRange(sp, maxRadiusCenter - maxRadius, maxRadiusCenter + maxRadius)).replace("|", "");
    }

    public String longestPalindromeGeeks(String s) {
        int N = s.length();
        if (N <= 1)
            return s;

        N = 2 * N + 1; // Position count
        int[] L = new int[N + 1]; // LPS Length Array
        L[0] = 0;
        L[1] = 1;
        int C = 1; // centerPosition
        int R = 2; // centerRightPosition
        int i = 0; // currentRightPosition
        int iMirror; // currentLeftPosition
        int maxLPSLength = 0;
        int maxLPSCenterPosition = 0;
        int start = -1;
        int end = -1;
        int diff = -1;

        // Uncomment it to print LPS Length array
        // printf("%d %d ", L[0], L[1]);
        for (i = 2; i < N; i++) {

            // get currentLeftPosition iMirror
            // for currentRightPosition i
            iMirror = 2 * C - i;
            L[i] = 0;
            diff = R - i;

            // If currentRightPosition i is within
            // centerRightPosition R
            if (diff > 0)
                L[i] = Math.min(L[iMirror], diff);

            // Attempt to expand palindrome centered at
            // currentRightPosition i. Here for odd positions,
            // we compare characters and if match then
            // increment LPS Length by ONE. If even position,
            // we just increment LPS by ONE without
            // any character comparison
            while (((i + L[i]) + 1 < N && (i - L[i]) > 0) &&
                    (((i + L[i] + 1) % 2 == 0) ||
                            (s.charAt((i + L[i] + 1) / 2) ==
                                    s.charAt((i - L[i] - 1) / 2)))) {
                L[i]++;
            }

            if (L[i] > maxLPSLength) // Track maxLPSLength
            {
                maxLPSLength = L[i];
                maxLPSCenterPosition = i;
            }

            // If palindrome centered at currentRightPosition i
            // expand beyond centerRightPosition R,
            // adjust centerPosition C based on expanded palindrome.
            if (i + L[i] > R) {
                C = i;
                R = i + L[i];
            }
        }

        start = (maxLPSCenterPosition - maxLPSLength) / 2;
        return String.copyValueOf(s.toCharArray(), start, maxLPSLength);
    }

    @Test
    void examples() {
        assertEquals("bab", longestPalindrome("babad"));
        assertEquals("bb", longestPalindrome("cbbd"));
    }
}
