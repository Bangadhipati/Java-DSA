import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        // 1. Split the path by slash
        // "/home//foo/" -> ["", "home", "", "foo", ""]
        String[] components = path.split("/");
        
        // Use Deque as a Stack
        Deque<String> stack = new ArrayDeque<>();
        
        for (String comp : components) {
            // Case 1: Ignore empty strings (from //) and current dir (.)
            if (comp.equals("") || comp.equals(".")) {
                continue;
            }
            
            // Case 2: Go up a level (..)
            if (comp.equals("..")) {
                // Only pop if stack is not empty (can't go higher than root)
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } 
            // Case 3: Valid directory name (push to stack)
            else {
                stack.addLast(comp);
            }
        }
        
        // 4. Reconstruct the path
        StringBuilder result = new StringBuilder();
        
        // Iterate through the stack (Deque iterates from First to Last by default)
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        
        // Edge Case: If stack is empty (e.g., input was "/../"), return root "/"
        return result.length() == 0 ? "/" : result.toString();
    }
}