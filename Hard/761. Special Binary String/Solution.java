import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String makeLargestSpecial(String s) {
        // Base case: empty string
        if (s == null || s.length() == 0) {
            return "";
        }

        List<String> components = new ArrayList<>();
        int balance = 0;
        int left = 0;

        // 1. Split into top-level special components
        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '1') {
                balance++;
            } else {
                balance--;
            }

            // When balance is 0, we found a standalone special substring
            if (balance == 0) {
                // 2. Recurse on the inside (excluding the mandatory outer 1 and 0)
                String inner = s.substring(left + 1, right);
                String maximizedInner = makeLargestSpecial(inner);
                
                // Re-attach the outer 1 and 0
                components.add("1" + maximizedInner + "0");
                
                // Move start pointer for the next component
                left = right + 1;
            }
        }

        // 3. Sort components in descending lexicographical order
        Collections.sort(components, Collections.reverseOrder());

        // 4. Concatenate and return
        StringBuilder sb = new StringBuilder();
        for (String comp : components) {
            sb.append(comp);
        }
        
        return sb.toString();
    }
}