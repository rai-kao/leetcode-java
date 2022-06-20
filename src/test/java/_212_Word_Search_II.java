import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/word-search-ii/">212. Word Search II</a>
 */
class _212_Word_Search_II {
    char[][] advance0;
    String[] advance1;
    List<String> advance2;

    char[][] advance3;
    String[] advance4;
    List<String> advance5;

    class TrieNode {
        public TrieNode[] children = new TrieNode[26];

        public TrieNode parent;

        public char val;

        public String word;
    }

    public TrieNode insert(String[] words) {
        var root = new TrieNode();
        for (var word : words) {
            var cur = root;
            for (var c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur.children[c - 'a'].parent = cur;
                cur.children[c - 'a'].val = c;
                cur = cur.children[c - 'a'];
            }
            cur.word = word;
        }
        return root;
    }

    public void remove(TrieNode node) {
        node.word = null;

        while (node.word == null && node.parent != null && Arrays.stream(node.children).allMatch(Objects::isNull)) {
            node.parent.children[node.val - 'a'] = null;
            node = node.parent;
        }
    }

    void find(char[][] board, int row, int col, TrieNode root, ArrayList<String> result) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length || root == null) {
            return;
        }

        var cur = board[row][col];
        if (cur == '*') {
            return;
        }

        var child = root.children[cur - 'a'];
        if (child == null) {
            return;
        }

        if (child.word != null) {
            result.add(child.word);
            remove(child);
        }

        board[row][col] = '*';
        find(board, row + 1, col, child, result);
        find(board, row - 1, col, child, result);
        find(board, row, col + 1, child, result);
        find(board, row, col - 1, child, result);
        board[row][col] = cur;
    }

    public List<String> findWords(char[][] board, String[] words) {
        var result = new ArrayList<String>();
        var root = insert(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                find(board, i, j, root, result);
            }
        }
        return result;
    }

    public void initialize() throws ConfigurationException {
        var configs = new Configurations();
        var config = configs.properties("_212_Word_Search_II.properties");
        advance0 = Arrays.stream(config.getString("advance0").split("\\],\\[")).map(e ->
                e.replaceAll("[\\[\\]\",]", "").toCharArray()).toArray(char[][]::new);
        advance1 = Arrays.stream(config.getString("advance1").split(",")).map(e ->
                e.replaceAll("\"", "")).toArray(String[]::new);
        advance2 = Arrays.stream(config.getString("advance2").split(",")).map(e ->
                e.replaceAll("\"", "")).collect(Collectors.toList());

        advance3 = Arrays.stream(config.getString("advance0").split("\\],\\[")).map(e ->
                e.replaceAll("[\\[\\]\",]", "").toCharArray()).toArray(char[][]::new);
        advance4 = Arrays.stream(config.getString("advance1").split(",")).map(e ->
                e.replaceAll("\"", "")).toArray(String[]::new);
        advance5 = Arrays.stream(config.getString("advance2").split(",")).map(e ->
                e.replaceAll("\"", "")).collect(Collectors.toList());
    }

    @Test
    void examples() {
        assertTrue(CommonUtils.compare(List.of("eat", "oath"), findWords(new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        }, new String[]{"oath", "pea", "eat", "rain"})));

        assertTrue(CommonUtils.compare(new ArrayList<>(), findWords(new char[][]{
                {'a', 'b'},
                {'c', 'd'}
        }, new String[]{"abcd"})));
    }

    @Test
    void advance() {
        assertDoesNotThrow(this::initialize);
        assertTrue(CommonUtils.compare(List.of("a"), findWords(new char[][]{{'a'}}, new String[]{"a"})));

        assertTrue(CommonUtils.compare(List.of("oath", "eat", "hklf", "hf"), findWords(new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        }, new String[]{"oath", "pea", "eat", "rain", "hklf", "hf"})));

        assertTrue(CommonUtils.compare(List.of("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"), findWords(new char[][]{
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'}
        }, new String[]{"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"})));

        assertTrue(CommonUtils.compare(advance2, findWords(advance0, advance1)));

        assertTrue(CommonUtils.compare(advance5, findWords(advance3, advance4)));
    }
}
