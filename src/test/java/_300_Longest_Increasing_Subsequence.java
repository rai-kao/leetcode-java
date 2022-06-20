import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/longest-increasing-subsequence/">300. Longest Increasing Subsequence</a>
 * @see <a href="https://en.wikipedia.org/wiki/Patience_sorting">Patience sorting</a>
 */
class _300_Longest_Increasing_Subsequence {
    public int lengthOfLIS(int[] nums) {
        var piles = new ArrayList<Integer>(nums.length);
        for (int num : nums) {
            int pile = Collections.binarySearch(piles, num);
            if (pile < 0) pile = ~pile;
            if (pile == piles.size()) {
                piles.add(num);
            } else {
                piles.set(pile, num);
            }
        }
        return piles.size();
    }

    @Test
    void examples() {
        assertEquals(4, lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        assertEquals(4, lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        assertEquals(1, lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }

    @Test
    void advance() {
        assertEquals(7, lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 200, 18, 101, 102, 103}));
    }
}
