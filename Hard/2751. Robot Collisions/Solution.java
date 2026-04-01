import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] ids = new Integer[n];
        
        // 1. Populate an array of indices
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        
        // 2. Sort the indices based on the physical positions of the robots (left to right)
        Arrays.sort(ids, (a, b) -> Integer.compare(positions[a], positions[b]));
        
        // Stack to keep track of the indices of robots moving RIGHT ('R')
        Deque<Integer> stack = new ArrayDeque<>();
        
        // 3. Process robots from left to right
        for (int id : ids) {
            if (directions.charAt(id) == 'R') {
                // Moving right, push to stack to wait for potential collisions
                stack.push(id);
            } else {
                // Moving left, simulate collisions with any 'R' robots we previously passed
                while (!stack.isEmpty() && healths[id] > 0) {
                    int top = stack.peek(); // The 'R' robot closest to us
                    
                    if (healths[id] > healths[top]) {
                        // 'L' wins, 'R' is destroyed
                        healths[id]--;
                        healths[top] = 0;
                        stack.pop();
                    } else if (healths[id] < healths[top]) {
                        // 'R' wins, 'L' is destroyed
                        healths[top]--;
                        healths[id] = 0;
                        // Stack is untouched because 'R' survived
                    } else {
                        // Draw, both are destroyed
                        healths[id] = 0;
                        healths[top] = 0;
                        stack.pop();
                    }
                }
            }
        }
        
        // 4. Collect the surviving robots in their ORIGINAL order
        List<Integer> survivors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                survivors.add(healths[i]);
            }
        }
        
        return survivors;
    }
}