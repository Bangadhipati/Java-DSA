import java.util.Iterator;
import java.util.TreeSet;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // TreeSet automatically keeps elements sorted and unique
        TreeSet<Integer> topThree = new TreeSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // 1. Add the Area 0 Rhombus (just the cell itself)
                topThree.add(grid[i][j]);
                if (topThree.size() > 3) {
                    topThree.pollFirst(); // Remove the smallest to maintain top 3
                }

                // 2. Expand and check larger rhombuses
                int L = 1; // L is the side length (number of cells between vertices)
                
                // Keep expanding as long as the bottom, left, and right vertices are within bounds
                while (i + 2 * L < m && j - L >= 0 && j + L < n) {
                    int sum = 0;
                    int r = i, c = j;

                    // Walk from Top to Right vertex
                    for (int k = 0; k < L; k++) sum += grid[r++][c++];
                    // Walk from Right to Bottom vertex
                    for (int k = 0; k < L; k++) sum += grid[r++][c--];
                    // Walk from Bottom to Left vertex
                    for (int k = 0; k < L; k++) sum += grid[r--][c--];
                    // Walk from Left back to Top vertex
                    for (int k = 0; k < L; k++) sum += grid[r--][c++];

                    // Add the calculated boundary sum
                    topThree.add(sum);
                    if (topThree.size() > 3) {
                        topThree.pollFirst();
                    }
                    
                    L++; // Increase the size of the rhombus for the next check
                }
            }
        }

        // 3. Extract the results in descending order
        int[] result = new int[topThree.size()];
        int idx = 0;
        Iterator<Integer> it = topThree.descendingIterator();
        while (it.hasNext()) {
            result[idx++] = it.next();
        }
        
        return result;
    }
}