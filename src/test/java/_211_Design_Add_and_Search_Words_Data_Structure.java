import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @see <a href="https://leetcode.com/problems/design-add-and-search-words-data-structure/">211. Design Add and Search Words Data Structure</a>
 */
class _211_Design_Add_and_Search_Words_Data_Structure {
    String[] advance0;
    String[][] advance1;
    Boolean[] advance2;

    class WordDictionary {
        static final int ALPHABET_SIZE = 26;

        static class TrieNode {
            TrieNode[] children = new TrieNode[ALPHABET_SIZE];

            boolean isEndOfWord;

            TrieNode() {
                isEndOfWord = false;
                for (int i = 0; i < ALPHABET_SIZE; i++) {
                    children[i] = null;
                }
            }
        }

        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            var cur = root;
            for (var c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.isEndOfWord = true;
        }

        boolean search(TrieNode root, char[] word, int index) {
            if (root == null) {
                return false;
            }
            if (index >= word.length) {
                return root.isEndOfWord;
            }
            if (word[index] != '.') {
                return search(root.children[word[index] - 'a'], word, index + 1);
            }

            for (var child : root.children) {
                if (search(child, word, index + 1)) {
                    return true;
                }
            }

            return false;
        }

        public boolean search(String word) {
            return search(root, word.toCharArray(), 0);
        }
    }

    Boolean[] process(String[] actions, String[][] inputs) {
        WordDictionary dic = null;
        var results = new ArrayList<Boolean>();
        for (var i = 0; i < actions.length; i++) {
            switch (actions[i]) {
                case "WordDictionary" -> {
                    dic = new WordDictionary();
                    results.add(null);
                }
                case "addWord" -> {
                    dic.addWord(inputs[i][0]);
                    results.add(null);
                }
                case "search" -> results.add(dic.search(inputs[i][0]));
            }
        }
        return results.toArray(Boolean[]::new);
    }

    public void initialize() throws ConfigurationException {
        var configs = new Configurations();
        var config = configs.properties("_211_Design_Add_and_Search_Words_Data_Structure.properties");
        advance0 = Arrays.stream(config.getString("advance0").split(",")).map(e ->
                e.replaceAll("\"", "")).toArray(String[]::new);
        advance1 = Arrays.stream(config.getString("advance1").split(",")).map(e ->
                new String[]{e.replaceAll("[\\[\\]\"]", "")}).toArray(String[][]::new);
        advance2 = Arrays.stream(config.getString("advance2").split(",")).map(e ->
                e.trim().equals("null") ? null : Boolean.parseBoolean(e.trim())).toArray(Boolean[]::new);
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
    @Test
    void examples() {
        assertArrayEquals(new Boolean[]{null, null, null, null, false, true, true, true}, process(
                new String[]{"WordDictionary", "addWord", "addWord", "addWord", "search", "search", "search", "search"},
                new String[][]{{}, {"bad"}, {"dad"}, {"mad"}, {"pad"}, {"bad"}, {".ad"}, {"b.."}}
        ));

        assertArrayEquals(new Boolean[]{null, null, null, null, null, false, false, null, true, true, false, false, true, false}, process(
                new String[]{"WordDictionary", "addWord", "addWord", "addWord", "addWord", "search", "search", "addWord", "search", "search", "search", "search", "search", "search"},
                new String[][]{{}, {"at"}, {"and"}, {"an"}, {"add"}, {"a"}, {".at"}, {"bat"}, {".at"}, {"an."}, {"a.d."}, {"b."}, {"a.d"}, {"."}}
        ));
    }

    @Test
    void advance() {
        assertDoesNotThrow(this::initialize);
        assertArrayEquals(advance2, process(advance0, advance1));
    }
}
