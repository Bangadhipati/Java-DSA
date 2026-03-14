import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        
        // Base case: The first row is always just [1]
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);
        
        // Build every subsequent row
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currentRow = new ArrayList<>();
            
            // 1. The first element is always 1
            currentRow.add(1);
            
            // 2. The inner elements are the sum of the two elements above
            for (int j = 1; j < i; j++) {
                currentRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            
            // 3. The last element is always 1
            currentRow.add(1);
            
            triangle.add(currentRow);
        }
        
        return triangle;
    }
}