import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @see <a href="https://leetcode.com/problems/find-median-from-data-stream/">295. Find Median from Data Stream</a>
 */
class _295_Find_Median_from_Data_Stream {
    String[] advance0;
    Integer[][] advance1;
    Double[] advance2;

    class MedianFinder {
        private final PriorityQueue<Integer> small = new PriorityQueue<>((a, b) -> b - a);
        private final PriorityQueue<Integer> large = new PriorityQueue<>();

        public double findMedian() {
            if (small.size() == large.size())
                return (small.peek() + large.peek()) / 2.0;
            else
                return small.size() > large.size() ? small.peek() : large.peek();
        }

        public void addNum(int num) {
            if (small.isEmpty() || num < small.peek()) {
                small.offer(num);
            } else {
                large.offer(num);
            }

            if (large.size() - small.size() >= 2) {
                small.offer(large.poll());
            } else if (small.size() - large.size() >= 2) {
                large.offer(small.poll());
            }
        }
    }

    Double[] process(String[] actions, Integer[][] inputs) {
        MedianFinder finder = null;
        var results = new ArrayList<Double>();
        for (var i = 0; i < actions.length; i++) {
            switch (actions[i]) {
                case "MedianFinder" -> {
                    finder = new MedianFinder();
                    results.add(null);
                }
                case "addNum" -> {
                    finder.addNum(inputs[i][0]);
                    results.add(null);
                }
                case "findMedian" -> results.add(finder.findMedian());
            }
        }
        return results.toArray(Double[]::new);
    }

    public void initialize() throws ConfigurationException {
        var configs = new Configurations();
        var config = configs.properties("_295_Find_Median_from_Data_Stream.properties");
        advance0 = Arrays.stream(config.getString("advance0").split(",")).map(e ->
                e.replaceAll("\"", "")).toArray(String[]::new);
        advance1 = Arrays.stream(config.getString("advance1").split(",")).map(e -> {
            var str = e.replaceAll("[\\[\\]\"]", "");
            return str.isEmpty() ? new Integer[]{} : new Integer[]{Integer.parseInt(str)};
        }).toArray(Integer[][]::new);
        advance2 = Arrays.stream(config.getString("advance2").split(",")).map(e ->
                e.trim().equals("null") ? null : Double.parseDouble(e.trim())).toArray(Double[]::new);
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
    @Test
    void examples() {
        assertArrayEquals(new Double[]{null, null, null, 1.5, null, 2.0}, process(new String[]{
                "MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"
        }, new Integer[][]{
                {}, {1}, {2}, {}, {3}, {}
        }));
    }

    @Test
    void advance() {
        assertDoesNotThrow(this::initialize);
        assertArrayEquals(new Double[]{null, null, null, 0.0}, process(new String[]{
                "MedianFinder", "addNum", "addNum", "findMedian"
        }, new Integer[][]{
                {}, {0}, {0}, {}
        }));

        assertArrayEquals(new Double[]{null, null, 6.00000, null, 8.00000, null, 6.00000, null, 6.00000, null, 6.00000, null, 5.50000, null, 6.00000, null, 5.50000, null, 5.00000, null, 4.00000, null, 3.00000}, process(new String[]{
                "MedianFinder", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian"
        }, new Integer[][]{
                {}, {6}, {}, {10}, {}, {2}, {}, {6}, {}, {5}, {}, {0}, {}, {6}, {}, {3}, {}, {1}, {}, {0}, {}, {0}, {}
        }));

        assertArrayEquals(new Double[]{null, null, 12.00000, null, 11.00000, null, 12.00000, null, 11.50000, null, 11.00000, null, 11.50000, null, 11.00000, null, 11.00000, null, 11.00000, null, 11.00000, null, 11.00000, null, 11.00000, null, 11.00000, null, 11.00000, null, 11.00000, null, 11.00000, null, 11.00000, null, 10.50000, null, 10.00000, null, 10.50000, null, 10.00000}, process(new String[]{
                "MedianFinder", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian"
        }, new Integer[][]{
                {}, {12}, {}, {10}, {}, {13}, {}, {11}, {}, {5}, {}, {15}, {}, {1}, {}, {11}, {}, {6}, {}, {17}, {}, {14}, {}, {8}, {}, {17}, {}, {6}, {}, {4}, {}, {16}, {}, {8}, {}, {10}, {}, {2}, {}, {12}, {}, {0}, {}
        }));

        assertArrayEquals(advance2, process(advance0, advance1));
    }
}
