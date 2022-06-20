import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/coin-change/">322. Coin Change</a>
 */
class _322_Coin_Change {
    public int coinChange(int[] coins, int amount) {
        var dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    @Test
    void examples() {
        assertEquals(3, coinChange(new int[]{1, 2, 5}, 11));
        assertEquals(-1, coinChange(new int[]{2}, 3));
        assertEquals(0, coinChange(new int[]{1}, 0));
    }

    @Test
    void advance() {
        assertEquals(2, coinChange(new int[]{1, 2147483647}, 2));
        assertEquals(4, coinChange(new int[]{2, 5, 10, 1}, 27));
        assertEquals(20, coinChange(new int[]{186, 419, 83, 408}, 6249));
        assertEquals(1, coinChange(new int[]{1}, 1));
        assertEquals(-1, coinChange(new int[]{2}, 1));
        assertEquals(24, coinChange(new int[]{411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422}, 9864));
    }
}
