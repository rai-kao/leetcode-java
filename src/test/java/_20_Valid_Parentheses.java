import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
class _20_Valid_Parentheses {
    boolean isValid(String s) {
        var stack = new Stack<Character>();
        var pair = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};

        for (var parenthesis : s.toCharArray()) {
            if (pair.containsKey(parenthesis)) {
                stack.push(parenthesis);
            } else {
                if (!stack.empty() && parenthesis == pair.get(stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    @Test
    void examples() {
        assertEquals(true, isValid("()"));
        assertEquals(true, isValid("()[]{}"));
        assertEquals(false, isValid("(]"));
    }

    @Test
    void advance() {
        // EmptyStackException
        assertEquals(false, isValid("]"));
    }
}
