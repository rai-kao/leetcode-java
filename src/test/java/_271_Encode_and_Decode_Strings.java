import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/encode-and-decode-strings/">271. Encode and Decode Strings</a>
 * @see <a href="https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/271-encode-and-decode-strings.html">271. Encode and Decode Strings</a>
 * @see <a href="https://www.lintcode.com/problem/659/">659 · Encode and Decode Strings</a>
 * <p>
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 * <p>
 * Machine 1 (sender) has the function:<br>
 * string encode(vector strs) {<br>
 * // ... your code<br>
 * return encoded_string;<br>
 * }<br>
 * Machine 2 (receiver) has the function:<br>
 * vector decode(string s) {<br>
 * //... your code<br>
 * return strs;<br>
 * }
 * <p>
 * So Machine 1 does:
 * <p>
 * string encoded_string = encode(strs);<br>
 * and Machine 2 does:
 * <p>
 * vector strs2 = decode(encoded_string);<br>
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * <p>
 * Implement the encode and decode methods.
 * <p>
 * Example1
 * <p>
 * Input: ["lint","code","love","you"]<br>
 * Output: ["lint","code","love","you"]<br>
 * Explanation:<br>
 * One possible encode method is: "lint:;code:;love:;you"
 * <p>
 * Example2
 * <p>
 * Input: ["we", "say", ":", "yes"]<br>
 * Output: ["we", "say", ":", "yes"]<br>
 * Explanation:<br>
 * One possible encode method is: "we:;say:;:::;yes"
 * <ul>
 *     Note:
 *     <li>The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
 *     <li>Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 *     <li>Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 * </ul>
 */
class _271_Encode_and_Decode_Strings {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        return String.join("%0A", strs);
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        return List.of(s.split("%0A"));
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.decode(codec.encode(strs));
    @Test
    void examples() {
        assertEquals(List.of("lint","code","love","you"), decode(encode(List.of("lint","code","love","you"))));
        assertEquals(List.of("we", "say", ":", "yes"), decode(encode(List.of("we", "say", ":", "yes"))));
        assertEquals(List.of(""), decode(encode(List.of(""))));
    }
}
