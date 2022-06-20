import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">121. Best Time to Buy and Sell Stock</a>
 */
class _121_Best_Time_to_Buy_and_Sell_Stock {
    int[] advance;

    int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];
        for (var p : prices) {
            min = Math.min(min, p);
            maxProfit = Math.max(maxProfit, p - min);
        }

        return maxProfit;
    }

    void initialize() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("_121_Best_Time_to_Buy_and_Sell_Stock.properties");
        properties.load(inputStream);
        advance = Arrays.stream(properties.getProperty("advance").split(",")).mapToInt(Integer::parseInt).toArray();
    }

    @Test
    void examples() {
        assertEquals(5, maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(0, maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    void advance() {
        assertDoesNotThrow(this::initialize);
        assertEquals(999, maxProfit(advance));
    }

}
