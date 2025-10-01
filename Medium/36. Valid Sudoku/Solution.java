class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Track digits seen in rows, columns, and boxes
        HashSet<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];
                if (val == '.') continue;

                // Encode uniqueness for row, column, and box
                String rowKey = val + " in row " + i;
                String colKey = val + " in col " + j;
                String boxKey = val + " in box " + (i / 3) + "-" + (j / 3);

                if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                    return false;
                }
            }
        }

        return true;
    }
}
