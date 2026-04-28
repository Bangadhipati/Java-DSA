import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Direction vectors mapping to: 0=Up, 1=Right, 2=Down, 3=Left
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // Allowed exit directions for each of the 6 street types (1-indexed to match grid values)
        int[][] exits = {
            {},        // 0: Placeholder
            {1, 3},    // 1: Right, Left
            {0, 2},    // 2: Up, Down
            {2, 3},    // 3: Down, Left
            {1, 2},    // 4: Right, Down
            {0, 3},    // 5: Up, Left
            {0, 1}     // 6: Up, Right
        };
        
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        
        // Start BFS from the top-left corner
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            // If we've reached the bottom-right cell, a valid path exists
            if (r == m - 1 && c == n - 1) {
                return true;
            }
            
            int currentStreet = grid[r][c];
            
            // Try moving through all valid exits for the current street piece
            for (int dir : exits[currentStreet]) {
                int nr = r + dirs[dir][0];
                int nc = c + dirs[dir][1];
                
                // Ensure the neighbor is within bounds and hasn't been visited yet
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int nextStreet = grid[nr][nc];
                    
                    // The neighbor MUST have an exit pointing back in the opposite direction
                    int requiredReturnDir = (dir + 2) % 4;
                    
                    boolean validHandshake = false;
                    for (int nextDir : exits[nextStreet]) {
                        if (nextDir == requiredReturnDir) {
                            validHandshake = true;
                            break;
                        }
                    }
                    
                    // If the handshake is valid, append it to the queue
                    if (validHandshake) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        
        // If the queue empties without reaching the destination
        return false;
    }
}