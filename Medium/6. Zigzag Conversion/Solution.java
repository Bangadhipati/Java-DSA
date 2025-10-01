class Solution {
    public String convert(String s, int numRows) {
        // Edge case: if only one row, return original string
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // Create a StringBuilder array for each row
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        // Iterate over characters in string
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            // Reverse direction if at first or last row
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            currentRow += goingDown ? 1 : -1;
        }

        // Merge all rows
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
