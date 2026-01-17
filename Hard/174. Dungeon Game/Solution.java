class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // dp[j] represents the minimum health needed at column j
        int[] dp = new int[n];

        // 1. Initialize the Bottom-Right Cell (Princess)
        // Knight needs at least 1 HP to stay alive.
        dp[n - 1] = 1 - dungeon[m - 1][n - 1];
        if (dp[n - 1] <= 0) dp[n - 1] = 1;

        // 2. Initialize the Last Row (can only move Right)
        for (int j = n - 2; j >= 0; j--) {
            int healthNeeded = dp[j + 1] - dungeon[m - 1][j];
            dp[j] = healthNeeded <= 0 ? 1 : healthNeeded;
        }

        // 3. Fill the rest of the grid Row by Row (Backward)
        for (int i = m - 2; i >= 0; i--) {
            // Update the last column of the current row (can only move Down)
            int healthNeededLastCol = dp[n - 1] - dungeon[i][n - 1];
            dp[n - 1] = healthNeededLastCol <= 0 ? 1 : healthNeededLastCol;

            for (int j = n - 2; j >= 0; j--) {
                // Knight chooses the path with the MINIMUM health requirement
                int res = (dp[j] < dp[j + 1] ? dp[j] : dp[j + 1]) - dungeon[i][j];
                dp[j] = res <= 0 ? 1 : res;
            }
        }

        return dp[0];
    }
}