import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/jump-game/">55. Jump Game</a>
 * @see <a href="https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/">Minimum number of jumps to reach end</a>
 */
class _55_Jump_Game {
    int[] longInput;

    boolean jump(int[] nums, int pos) {
        if (pos + nums[pos] >= nums.length - 1) {
            return true;
        }

        var ret = false;
        for (int i = 1; i <= nums[pos]; i++) {
            if (jump(nums, pos + i)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    int minJumps(int[] nums, int n) {
        // jumps[n-1] will hold the
        int jumps[] = new int[n];
        // result
        int i, j;

        // if first element is 0,
        if (n == 0 || nums[0] == 0)
            return Integer.MAX_VALUE;
        // end cannot be reached

        jumps[0] = 0;

        // Find the minimum number of jumps to reach nums[i]
        // from nums[0], and assign this value to jumps[i]
        for (i = 1; i < n; i++) {
            jumps[i] = Integer.MAX_VALUE;
            for (j = 0; j < i; j++) {
                if (i <= j + nums[j] && jumps[j] != Integer.MAX_VALUE) {
                    jumps[i] = Math.min(jumps[i], jumps[j] + 1);
                    break;
                }
            }
        }
        return jumps[n - 1];
    }

    boolean canJump(int[] nums) {
        int maxLocation = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxLocation < i) {
                return false; // if previous maxLocation smaller than i, meaning we cannot reach location i, thus return false.
            }
            maxLocation = Math.max((i + nums[i]), maxLocation); // greedy:
        }
        return true;
    }

    void initialize() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("_55_Jump_Game.properties");
        properties.load(inputStream);
        var value = properties.getProperty("advance");
        longInput = Arrays.stream(value.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    @Test
    void examples() {
        assertEquals(true, canJump(new int[]{2, 3, 1, 1, 4}));
        assertEquals(false, canJump(new int[]{3, 2, 1, 0, 4}));
    }

    @Test
    void advance() {
        assertDoesNotThrow(this::initialize);
        assertEquals(true, canJump(new int[]{8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5}));
        assertEquals(false, canJump(longInput));
    }
}
