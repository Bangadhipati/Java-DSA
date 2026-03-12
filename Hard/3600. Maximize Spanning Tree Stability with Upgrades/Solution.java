class Solution {
    class DSU {
        int[] parent, rank;
        int components;
        
        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        
        public int find(int i) {
            if (parent[i] == i) return i;
            // Path compression
            return parent[i] = find(parent[i]);
        }
        
        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            
            if (rootI != rootJ) {
                // Union by rank
                if (rank[rootI] < rank[rootJ]) {
                    parent[rootI] = rootJ;
                } else if (rank[rootI] > rank[rootJ]) {
                    parent[rootJ] = rootI;
                } else {
                    parent[rootJ] = rootI;
                    rank[rootI]++;
                }
                components--;
                return true;
            }
            return false;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        // 1. Initial sanity check: Can we even form a spanning tree at all?
        DSU initialCheck = new DSU(n);
        for (int[] e : edges) {
            if (e[3] == 1) {
                // If mandatory edges form a cycle, a valid spanning tree is impossible
                if (!initialCheck.union(e[0], e[1])) return -1;
            }
        }
        for (int[] e : edges) {
            if (e[3] == 0) {
                initialCheck.union(e[0], e[1]);
            }
        }
        // If we can't connect all nodes using all possible edges, return -1
        if (initialCheck.components > 1) return -1;

        // 2. Binary Search for the maximum minimum-strength
        int low = 1;
        // Max initial strength is 10^5. Doubled is 2 * 10^5.
        int high = 200000; 
        int bestStability = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canAchieveTarget(n, edges, k, mid)) {
                bestStability = mid; // This stability works, record it
                low = mid + 1;       // Try to push for an even higher stability
            } else {
                high = mid - 1;      // It's too high, lower our expectations
            }
        }

        return bestStability;
    }

    private boolean canAchieveTarget(int n, int[][] edges, int k, int target) {
        DSU dsu = new DSU(n);
        int edgesAdded = 0;
        int upgradesUsed = 0;

        // Step A: Add all mandatory edges
        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < target) return false; // Mandatory edge fails the target
                if (dsu.union(e[0], e[1])) {
                    edgesAdded++;
                }
            }
        }

        // Step B: Greedily add 0-cost optional edges (strength >= target)
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] >= target) {
                if (dsu.union(e[0], e[1])) {
                    edgesAdded++;
                }
            }
        }

        // Step C: Add 1-cost optional edges (need upgrading to hit target)
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] < target && e[2] * 2 >= target) {
                if (dsu.union(e[0], e[1])) {
                    edgesAdded++;
                    upgradesUsed++;
                }
            }
        }

        // We successfully built a tree if we added n-1 edges AND didn't exceed k upgrades
        return edgesAdded == n - 1 && upgradesUsed <= k;
    }
}