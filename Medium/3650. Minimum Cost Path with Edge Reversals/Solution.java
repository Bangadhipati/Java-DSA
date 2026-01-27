import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {
        // Linked Adjacency List using arrays for 100% memory efficiency
        int m = edges.length;
        int[] head = new int[n];
        Arrays.fill(head, -1);
        
        // Each edge can be normal or reversed, so we need space for 2 * m connections
        int[] next = new int[2 * m];
        int[] to = new int[2 * m];
        int[] weight = new int[2 * m];
        int edgeIdx = 0;

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            
            // Normal edge: u -> v (cost w)
            to[edgeIdx] = v;
            weight[edgeIdx] = w;
            next[edgeIdx] = head[u];
            head[u] = edgeIdx++;
            
            // Reversible edge: v -> u becomes u -> v (cost 2w)
            to[edgeIdx] = u;
            weight[edgeIdx] = 2 * w;
            next[edgeIdx] = head[v];
            head[v] = edgeIdx++;
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        // PriorityQueue stores long[]: {node_index, distance}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long d = curr[1];

            if (d > dist[u]) continue;
            if (u == n - 1) break; // Reached target

            for (int i = head[u]; i != -1; i = next[i]) {
                int v = to[i];
                int w = weight[i];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }

        if (dist[n - 1] == Long.MAX_VALUE) return -1;
        return (int) dist[n - 1]; // Cast back to int for the driver
    }
}