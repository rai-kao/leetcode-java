import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/combination-sum/">39. Combination Sum</a>
 * @see <a href="https://leetcode.com/problems/combination-sum/discuss/1777569/FULL-EXPLANATION-WITH-STATE-SPACE-TREE-oror-Recursion-and-Backtracking-oror-Well-Explained-oror-C%2B%2B">🚀FULL EXPLANATION WITH STATE SPACE TREE || Recursion and Backtracking || Well Explained || C++</a>
 */
class _39_Combination_Sum {
    void subsetSum(int[] candidates, int target, List<List<Integer>> combinations, int left, int sum, List<Integer> subset) {
        for (int i = left; i < candidates.length; i++) {
            var curSum = sum + candidates[i];
            if (curSum > target) {
                break;
            }

            if (curSum == target) {
                var ans = new ArrayList<>(subset);
                ans.add(candidates[i]);
                combinations.add(ans);
                break;
            }
            subset.add(candidates[i]);
            subsetSum(candidates, target, combinations, i, curSum, subset);
            subset.remove(subset.size() - 1);
        }
    }

    List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        subsetSum(candidates, target, ret, 0, 0, new ArrayList<>());
        return ret;
    }

    @Test
    void examples() {
        assertTrue(CommonUtils.compare(List.of(
                List.of(2, 2, 3),
                List.of(7)
        ), combinationSum(new int[]{2, 3, 6, 7,}, 7)));
        assertTrue(CommonUtils.compare(List.of(
                List.of(2, 2, 2, 2),
                List.of(2, 3, 3),
                List.of(3, 5)
        ), combinationSum(new int[]{2, 3, 5}, 8)));
        assertTrue(CommonUtils.compare(List.of(), combinationSum(new int[]{2}, 1)));
    }

    @Test
    void advance() {
        assertTrue(CommonUtils.compare(List.of(
                List.of(1, 1, 1, 1, 1, 1, 1, 1, 1),
                List.of(1, 1, 1, 1, 1, 1, 1, 2),
                List.of(1, 1, 1, 1, 1, 1, 3),
                List.of(1, 1, 1, 1, 1, 2, 2),
                List.of(1, 1, 1, 1, 2, 3),
                List.of(1, 1, 1, 1, 5),
                List.of(1, 1, 1, 2, 2, 2),
                List.of(1, 1, 1, 3, 3),
                List.of(1, 1, 1, 6),
                List.of(1, 1, 2, 2, 3),
                List.of(1, 1, 2, 5),
                List.of(1, 1, 7),
                List.of(1, 2, 2, 2, 2),
                List.of(1, 2, 3, 3),
                List.of(1, 2, 6),
                List.of(1, 3, 5),
                List.of(2, 2, 2, 3),
                List.of(2, 2, 5),
                List.of(2, 7),
                List.of(3, 3, 3),
                List.of(3, 6)
        ), combinationSum(new int[]{2, 7, 6, 3, 5, 1,}, 9)));
    }
}
