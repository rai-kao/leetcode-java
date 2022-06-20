import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @see <a href="https://leetcode.com/problems/implement-trie-prefix-tree/">208. Implement Trie (Prefix Tree)</a>
 */
class _208_Implement_Trie_Prefix_Tree_ {
    class Trie {
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

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            var cur = root;
            for (var i : word.toCharArray()) {
                if (cur.children[i - 'a'] == null) {
                    cur.children[i - 'a'] = new TrieNode();
                }
                cur = cur.children[i - 'a'];
            }
            cur.isEndOfWord = true;
        }

        public boolean search(String word) {
            var cur = root;
            for (var i : word.toCharArray()) {
                if (cur.children[i - 'a'] == null) {
                    return false;
                }
                cur = cur.children[i - 'a'];
            }
            return cur.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            var cur = root;
            for (var i : prefix.toCharArray()) {
                if (cur.children[i - 'a'] == null) {
                    return false;
                }
                cur = cur.children[i - 'a'];
            }
            return true;
        }
    }

    Boolean[] process(String[] actions, String[][] inputs) {
        Trie trie = null;
        var results = new ArrayList<Boolean>();
        for (var i = 0; i < actions.length; i++) {
            switch (actions[i]) {
                case "Trie" -> {
                    trie = new Trie();
                    results.add(null);
                }
                case "insert" -> {
                    trie.insert(inputs[i][0]);
                    results.add(null);
                }
                case "search" -> results.add(trie.search(inputs[i][0]));
                case "startsWith" -> results.add(trie.startsWith(inputs[i][0]));
            }
        }
        return results.toArray(Boolean[]::new);
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    @Test
    void examples() {
        assertArrayEquals(new Boolean[]{null, null, true, false, true, null, true}, process(
                new String[]{"Trie", "insert", "search", "search", "startsWith", "insert", "search"},
                new String[][]{{}, {"apple"}, {"apple"}, {"app"}, {"app"}, {"app"}, {"app"}}));
    }
}
