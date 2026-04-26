class Solution {
    // Direction vectors for moving: Up, Down, Left, Right
    private final int[] dr = {-1, 1, 0, 0};
    private final int[] dc = {0, 0, -1, 1};

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        // Sweep the grid to ensure we check every disconnected component
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    // Start a DFS. We pass -1, -1 as the initial "parent" coordinates
                    if (dfs(grid, visited, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] grid, boolean[][] visited, int r, int c, int pr, int pc, char targetChar) {
        // Mark the current cell as visited
        visited[r][c] = true;
        
        // Explore all 4 adjacent directions
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            // Check boundaries and ensure the character matches
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == targetChar) {
                
                // If it's an unvisited neighbor, recursively explore it
                if (!visited[nr][nc]) {
                    if (dfs(grid, visited, nr, nc, r, c, targetChar)) {
                        return true;
                    }
                } 
                // If it IS visited, and it is NOT the cell we just came from, we found a cycle!
                else if (nr != pr || nc != pc) {
                    return true;
                }
            }
        }
        
        return false;
    }
}