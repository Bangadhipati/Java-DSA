import java.util.Arrays;

class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        
        // pref[r][c] stores the sum of grid[0...r-1][c] for O(1) range queries
        long[][] pref = new long[n + 1][n];
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n; r++) {
                pref[r + 1][c] = pref[r][c] + grid[r][c];
            }
        }

        // dp[prev][curr] tracks the max score up to the previous column, 
        // given h_{j-1} = prev and h_j = curr
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // Base case: Before column 0, we can imagine a column -1 with 0 black cells
        for (int curr = 0; curr <= n; curr++) {
            dp[0][curr] = 0;
        }

        // Sweep from left to right across the columns
        for (int j = 0; j < n; j++) {
            long[][] nextDp = new long[n + 1][n + 1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(nextDp[i], -1);
            }

            for (int curr = 0; curr <= n; curr++) {
                // Precompute running Prefix Maxes for Part 1 (where prev <= next)
                long[] prefMax = new long[n + 1];
                long currentPreMax = -1;
                for (int prev = 0; prev <= n; prev++) {
                    if (dp[prev][curr] != -1) {
                        currentPreMax = Math.max(currentPreMax, dp[prev][curr]);
                    }
                    prefMax[prev] = currentPreMax;
                }

                // Precompute running Suffix Maxes for Part 2 (where prev > next)
                long[] suffMax = new long[n + 1];
                long currentSufMax = -1;
                for (int prev = n; prev >= 0; prev--) {
                    if (dp[prev][curr] != -1) {
                        // If prev > next, the score only depends on prev
                        long score = pref[Math.max(curr, prev)][j] - pref[curr][j];
                        currentSufMax = Math.max(currentSufMax, dp[prev][curr] + score);
                    }
                    suffMax[prev] = currentSufMax;
                }

                // Calculate the max score for all possible heights of the NEXT column
                for (int nxt = 0; nxt <= n; nxt++) {
                    long best = -1;
                    
                    // Case 1: prev <= nxt
                    // The max boundary is entirely dictated by `nxt`, so the score doesn't depend on `prev`
                    if (prefMax[nxt] != -1) {
                        long score = pref[Math.max(curr, nxt)][j] - pref[curr][j];
                        best = Math.max(best, prefMax[nxt] + score);
                    }
                    
                    // Case 2: prev > nxt
                    // The max boundary is dictated by `prev`, so we rely on our suffix max precomputation
                    if (nxt + 1 <= n && suffMax[nxt + 1] != -1) {
                        best = Math.max(best, suffMax[nxt + 1]);
                    }
                    
                    nextDp[curr][nxt] = best;
                }
            }
            dp = nextDp;
        }

        // We've processed the last column (j = n - 1). 
        // The imaginary column 'n' off the right edge will always have 0 black cells (nxt = 0).
        long maxScore = 0;
        for (int curr = 0; curr <= n; curr++) {
            maxScore = Math.max(maxScore, dp[curr][0]);
        }
        
        return maxScore;
    }
}