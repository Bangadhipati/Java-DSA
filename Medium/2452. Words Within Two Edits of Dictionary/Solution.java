public import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        
        for (String q : queries) {
            for (String d : dictionary) {
                int diff = 0;
                
                // Compare character by character
                for (int i = 0; i < q.length(); i++) {
                    if (q.charAt(i) != d.charAt(i)) {
                        diff++;
                    }
                    
                    // Short-circuit 1: If it takes more than 2 edits, stop checking this word
                    if (diff > 2) {
                        break;
                    }
                }
                
                // Short-circuit 2: If we found a valid match, add the query and stop checking the dictionary
                if (diff <= 2) {
                    result.add(q);
                    break; 
                }
            }
        }
        
        return result;
    }
} Solution {
    
}
