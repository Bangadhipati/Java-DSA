import java.util.*;

class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length();
        
        // 1. Map unique strings to IDs
        Map<String, Integer> strToId = new HashMap<>();
        int idCounter = 0;
        for (String s : original) if (!strToId.containsKey(s)) strToId.put(s, idCounter++);
        for (String s : changed) if (!strToId.containsKey(s)) strToId.put(s, idCounter++);
        
        int m = idCounter;
        long[][] dist = new long[m][m];
        for (long[] row : dist) Arrays.fill(row, Long.MAX_VALUE / 2);
        for (int i = 0; i < m; i++) dist[i][i] = 0;

        // 2. Build Graph and Floyd-Warshall
        for (int i = 0; i < original.length; i++) {
            int u = strToId.get(original[i]);
            int v = strToId.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], (long) cost[i]);
        }
        
        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 3. DP with substring lengths
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;

        // Extract unique lengths to speed up inner loop
        Set<Integer> lengthsSet = new HashSet<>();
        for (String s : original) lengthsSet.add(s.length());
        Integer[] lengths = lengthsSet.toArray(new Integer[0]);

        for (int i = 0; i < n; i++) {
            if (dp[i] >= Long.MAX_VALUE / 2) continue;

            // Option 1: Characters are identical
            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }

            // Option 2: Try converting substrings starting at i
            for (int len : lengths) {
                if (i + len <= n) {
                    String subS = source.substring(i, i + len);
                    String subT = target.substring(i, i + len);
                    
                    if (strToId.containsKey(subS) && strToId.containsKey(subT)) {
                        int u = strToId.get(subS);
                        int v = strToId.get(subT);
                        if (dist[u][v] < Long.MAX_VALUE / 2) {
                            dp[i + len] = Math.min(dp[i + len], dp[i] + dist[u][v]);
                        }
                    }
                }
            }
        }

        return dp[n] >= Long.MAX_VALUE / 2 ? -1 : dp[n];
    }
}