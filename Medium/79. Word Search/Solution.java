class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        char[] wordChars = word.toCharArray(); // Faster than word.charAt()

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // Only start DFS if the first character matches
                if (dfs(board, r, c, wordChars, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int r, int c, char[] word, int index) {
        // Base Case: We found the whole word
        if (index == word.length) {
            return true;
        }

        // Boundary and Match checks
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word[index]) {
            return false;
        }

        // Mark current cell as visited
        char temp = board[r][c];
        board[r][c] = '\0'; 

        // Explore 4 directions
        boolean found = dfs(board, r + 1, c, word, index + 1) ||
                        dfs(board, r - 1, c, word, index + 1) ||
                        dfs(board, r, c + 1, word, index + 1) ||
                        dfs(board, r, c - 1, word, index + 1);

        // Backtrack: Restore the original character
        board[r][c] = temp;

        return found;
    }
}