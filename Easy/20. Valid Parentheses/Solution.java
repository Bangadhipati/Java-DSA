import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '{') {
                stack.push('}');
            } else if (ch == '[') {
                stack.push(']');
            } else {
                // If stack empty or top doesn't match current char
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        
        // String is valid only if no unmatched brackets remain
        return stack.isEmpty();
    }
}
