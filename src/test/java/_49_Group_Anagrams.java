import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/group-anagrams/">49. Group Anagrams</a>
 */
class _49_Group_Anagrams {
    String countKey(String input) {
        char[] freq = new char[26];
        for (int i = 0; i < input.length(); i++) {
            freq[input.charAt(i) - 'a']++;
        }
        return String.copyValueOf(freq);
    }

    String orderedKey(String input) {
        var chars = input.toCharArray();
        Arrays.sort(chars);
        return String.copyValueOf(chars);
    }

    Long primeKey(String input) {
        final int[] prime = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 107};
        var hash = 1L;
        for (int i = 0; i < input.length(); i++) {
            hash *= prime[input.charAt(i) - 'a'];
        }
        return hash;
    }

    List<List<String>> groupAnagrams(String[] strs) {
        var ret = new HashMap<String, ArrayList<String>>();
        for (var str : strs) {
            var key = orderedKey(str);
            if (!ret.containsKey(key)) {
                var bucket = new ArrayList<String>();
                bucket.add(str);
                ret.put(key, bucket);
            } else {
                ret.get(key).add(str);
            }
        }
        return ret.values().stream().map(ArrayList::new).collect(Collectors.toList());
    }

    @Test
    void examples() {
        assertTrue(CommonUtils.compare(new String[][]{
                {"bat"},
                {"nat", "tan"},
                {"ate", "eat", "tea"}
        }, groupAnagrams(new String[]{
                "eat", "tea", "tan", "ate", "nat", "bat"
        })));

        assertTrue(CommonUtils.compare(new String[][]{
                {""}
        }, groupAnagrams(new String[]{
                ""
        })));

        assertTrue(CommonUtils.compare(new String[][]{
                {"a"},
        }, groupAnagrams(new String[]{
                "a"
        })));
    }

    @Test
    void advance() {
        assertTrue(CommonUtils.compare(new String[][]{
                {"max"}, {"buy"}, {"doc"}, {"may"}, {"ill"}, {"duh"}, {"tin"}, {"bar"}, {"pew"}, {"cab"}
        }, groupAnagrams(new String[]{
                "cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"
        })));

        assertTrue(CommonUtils.compare(new String[][]{
                {"wee"}, {"pep"}, {"cub"}, {"eco"}, {"dem"}, {"gap"}, {"vet"}, {"job"}, {"ben"}, {"toe"}, {"hay", "hay"}, {"mes"}, {"ads"}, {"alb"}, {"wot"}, {"gem"}, {"oaf"}, {"hop"}, {"ton"}, {"pug"}, {"end"}, {"con"}, {"coy"}, {"sat"}, {"soy"}, {"pay"}, {"tin"}, {"pie"}, {"ape"}, {"tho"}, {"erg"}, {"sen"}, {"rig", "rig"}, {"tap"}, {"wac"}, {"gog"}, {"led"}, {"zen"}, {"arc"}, {"lie"}, {"kid"}
        }, groupAnagrams(new String[]{
                "tho", "tin", "erg", "end", "pug", "ton", "alb", "mes", "job", "ads", "soy", "toe", "tap", "sen", "ape", "led", "rig", "rig", "con", "wac", "gog", "zen", "hay", "lie", "pay", "kid", "oaf", "arc", "hay", "vet", "sat", "gap", "hop", "ben", "gem", "dem", "pie", "eco", "cub", "coy", "pep", "wot", "wee"
        })));
    }
}
