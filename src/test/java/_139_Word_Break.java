import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @see <a href="https://leetcode.com/problems/word-break/">139. Word Break</a>
 */
class _139_Word_Break {
    /**
     * Word break use minimum words
     *
     * @param s
     * @param wordDict
     * @return Used words = reault - 1
     */
    boolean wordBreakWithMinWords(String s, List<String> wordDict) {
        var dp = new int[s.length() + 1];
        var set = new HashSet<>(wordDict);

        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] > 0 && set.contains(s.substring(j, i)) ? dp[j] + 1 : 0;
                if (dp[i] > 0) break;
            }
        }
        return dp[s.length()] > 0;
    }

    /**
     * Word break use maximum words
     *
     * @param s
     * @param wordDict
     * @return Used words = reault - 1
     */
    boolean wordBreakWithMaxWords(String s, List<String> wordDict) {
        var dp = new int[s.length() + 1];
        var set = new HashSet<>(wordDict);

        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = dp[j] > 0 && set.contains(s.substring(j, i)) ? dp[j] + 1 : 0;
                if (dp[i] > 0) break;
            }
        }
        return dp[s.length()] > 0;
    }

    boolean wordBreak(String s, List<String> wordDict) {
        var dic = new HashSet<>(wordDict);
        var dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = dp[j] && dic.contains(s.substring(j, i));
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    @Test
    void examples() {
        assertTrue(wordBreak("leetcode", List.of("leet", "code")));
        assertTrue(wordBreak("applepenapple", List.of("apple", "pen")));
        assertFalse(wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
    }

    @Test
    void advance() {
        assertFalse(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                List.of("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));

        assertFalse(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                List.of("aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "ba")));
    }
}
