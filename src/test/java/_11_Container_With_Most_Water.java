import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * https://www.geeksforgeeks.org/container-with-most-water/
 */
class _11_Container_With_Most_Water {
    int[] longInput;

    public int maxArea(int[] height) {
        var l = 0;
        var r = height.length - 1;
        var max = 0;
        while (l < r) {
            var area = Math.min(height[l], height[r]) * (r - l);
            if (area > max) {
                max = area;
            }
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return max;
    }

    public void initialize() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("_11_Container_With_Most_Water.properties");
        properties.load(inputStream);
        var value = properties.getProperty("advance");
        longInput = Arrays.stream(value.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    @Test
    void examples() {
        assertEquals(49, maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(1, maxArea(new int[]{1, 1}));
    }

    @Test
    void advance() {
        assertDoesNotThrow(this::initialize);
        assertEquals(705634720, maxArea(longInput));
    }
}
