import java.util.Arrays;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // 1. Initialize the distance matrix for 26 letters
        long[][] dist = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE / 2); // Divide by 2 to prevent overflow during addition
            dist[i][i] = 0;
        }

        // 2. Fill in the direct transformation costs
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], (long) cost[i]);
        }

        // 3. Floyd-Warshall Algorithm: Find shortest path between all pairs
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 4. Calculate total cost for the string conversion
        long totalCost = 0;
        int n = source.length();
        for (int i = 0; i < n; i++) {
            int u = source.charAt(i) - 'a';
            int v = target.charAt(i) - 'a';
            
            if (u == v) continue;
            
            if (dist[u][v] >= Long.MAX_VALUE / 2) {
                return -1; // Transformation impossible
            }
            totalCost += dist[u][v];
        }

        return totalCost;
    }
}