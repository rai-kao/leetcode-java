import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/alien-dictionary/">269. Alien Dictionary</a>
 * @see <a href="https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/269.%20Alien%20Dictionary.md">Algorithm-and-Leetcode/leetcode/269. Alien Dictionary.md</a>
 * @see <a href="https://www.lintcode.com/problem/892/">892 · Alien Dictionary</a>
 * <p>
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * ["wrt","wrf","er","ett","rftt"]
 * <p>
 * Output: "wertf"
 * <p>
 * Example 2:
 * <p>
 * Input:
 * ["z","x"]
 * <p>
 * Output: "zx"
 * <p>
 * Example 3:
 * <p>
 * Input:
 * ["z","x","z"]
 * <p>
 * Output: ""
 * <p>
 * Explanation: The order is invalid, so return "".
 * <ul>
 *     Note:
 *     <li>You may assume all letters are in lowercase.
 *     <li>You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 *     <li>If the order is invalid, return an empty string.
 *     <li>There may be multiple valid order of letters, return any one of them is fine.
 * </ul>
 */
class _269_Alien_Dictionary {
    public String alienOrder(String[] words) {
        var incoming = new HashMap<Character, Integer>();
        for (var w : words) {
            for (var c : w.toCharArray()) {
                incoming.put(c, 0);
            }
        }

        var graph = new HashMap<Character, Set<Character>>();
        var prev = words[0];
        for (var i = 1; i < words.length; i++) {
            for (var j = 0; j < words[i].length() && j < prev.length(); j++) {
                if (prev.charAt(j) != words[i].charAt(j)) {
                    var endSet = graph.getOrDefault(prev.charAt(j), new HashSet<>());
                    endSet.add(words[i].charAt(j));
                    incoming.put(words[i].charAt(j), incoming.get(words[i].charAt(j)) + 1);
                    graph.put(prev.charAt(j), endSet);
                    break;
                }
            }
            prev = words[i];
        }

        var result = new StringBuilder();
        var queue = new LinkedList<Character>();
        for (var inc : incoming.keySet()) {
            if (incoming.get(inc) == 0) {
                queue.add(inc);
            }
        }

        var edges = graph.size();
        while (!queue.isEmpty()) {
            var course = queue.poll();
            result.append(course);
            if (!graph.containsKey(course)) {
                continue;
            }
            for (var adj : graph.get(course)) {
                edges--;
                incoming.put(adj, incoming.get(adj) - 1);
                if (incoming.get(adj) == 0) {
                    queue.add(adj);
                }
            }
        }
        return edges == 0 ? result.toString() : "";
    }

    public String alienOrderTopoSort(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0);
            }
        }
        for (int i = 1; i < words.length; i++) {
            int maxLen = Math.min(words[i].length(), words[i - 1].length());
            char[] arr1 = words[i - 1].toCharArray(), arr2 = words[i].toCharArray();
            for (int j = 0; j < maxLen; j++) {
                if (arr1[j] != arr2[j]) {
                    Set<Character> set = adj.containsKey(arr1[j]) ? adj.get(arr1[j]) : new HashSet<>();
                    if (!set.contains(arr2[j])) {
                        set.add(arr2[j]);
                        adj.put(arr1[j], set);
                        indegree.put(arr2[j], indegree.get(arr2[j]) + 1);
                    }
                    break;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (Character c : indegree.keySet()) {
            if (indegree.get(c) == 0) q.offer(c);
        }
        StringBuilder result = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.poll();
            result.append(c);
            if (adj.get(c) == null) continue;
            for (Character neigh : adj.get(c)) {
                indegree.put(neigh, indegree.get(neigh) - 1);
                if (indegree.get(neigh) == 0) q.offer(neigh);
            }
        }
        for (int count : indegree.values()) {
            if (count > 0) return "";
        }
        return result.toString();
    }

    public String alienOrderAlt(String[] words) {
        Map<Character, Integer> indegree = new HashMap<>(); // key: letter, value: its indegree
        Map<Character, Set<Character>> map = new HashMap<>(); // key: request, value: its childrens
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0);
            }
        }
        for (int i = 1; i < words.length; i++) {
            int minLen = Math.min(words[i].length(), words[i - 1].length());
            char[] arr1 = words[i - 1].toCharArray(), arr2 = words[i].toCharArray();
            for (int j = 0; j < minLen; j++) {
                if (arr1[j] != arr2[j]) { //arr1[j] is ahead of arr2[j]
                    Set<Character> set = map.getOrDefault(arr1[j], new HashSet<>());
                    if (!set.contains(arr2[j])) indegree.put(arr2[j], indegree.get(arr2[j]) + 1);
                    set.add(arr2[j]);
                    map.put(arr1[j], set);
                    break;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.offer(entry.getKey());
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char cur = q.poll();
            sb.append(cur);
            if (!map.containsKey(cur)) continue; // current character doesn't have neighbour.
            Set<Character> neighbours = map.get(cur);
            for (char c : neighbours) {
                indegree.put(c, indegree.get(c) - 1);
                if (indegree.get(c) == 0) q.offer(c);
            }
        }
        return sb.length() == indegree.size() ? sb.toString() : "";
    }

    @Test
    void examples() {
        assertEquals("wertf", alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        assertEquals("zx", alienOrder(new String[]{"z", "x"}));
        assertEquals("", alienOrder(new String[]{"z", "x", "z"}));
    }

    @Test
    void advance() {
        assertEquals(alienOrderAlt(new String[]{"zy", "zx"}), alienOrder(new String[]{"zy", "zx"}));
    }
}
