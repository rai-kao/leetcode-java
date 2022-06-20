import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/top-k-frequent-elements/">347. Top K Frequent Elements</a>
 */
class _347_Top_K_Frequent_Elements {
    public int[] topKFrequent(int[] nums, int k) {
        var map = new HashMap<Integer, Integer>();
        for (var num:nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        var pair = new PriorityQueue<int[]>((a, b) -> b[1]- a[1]);
        for (var key:map.keySet()) {
            pair.add(new int[]{key, map.get(key)});
        }
        var res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pair.poll()[0];
        }
        return res;
    }

    @Test
    void examples() {
        assertArrayEquals(new int[]{1, 2}, topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        assertArrayEquals(new int[]{1}, topKFrequent(new int[]{1}, 1));
    }
}
