import java.util.HashMap;
import java.util.Map;

class Solution {
    int[] parent;

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        
        // 1. Initialize Union-Find structure
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Every index is initially its own root
        }
        
        // Connect the allowed swap indices into components
        for (int[] swap : allowedSwaps) {
            union(swap[0], swap[1]);
        }
        
        // 2. Build the shared inventory of available numbers for each connected component
        // Outer Map: Component Root Index -> Inner Map: (Value -> Frequency Count)
        Map<Integer, Map<Integer, Integer>> rootToCounts = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int root = find(i);
            rootToCounts.putIfAbsent(root, new HashMap<>());
            
            Map<Integer, Integer> pool = rootToCounts.get(root);
            pool.put(source[i], pool.getOrDefault(source[i], 0) + 1);
        }
        
        // 3. Verify target requirements against the available inventory pools
        int hammingDistance = 0;
        
        for (int i = 0; i < n; i++) {
            int root = find(i);
            Map<Integer, Integer> pool = rootToCounts.get(root);
            int needed = target[i];
            
            // If the pool has the number we need, consume it
            if (pool.getOrDefault(needed, 0) > 0) {
                pool.put(needed, pool.get(needed) - 1);
            } else {
                // If the pool doesn't have it, we have a permanent mismatch
                hammingDistance++;
            }
        }
        
        return hammingDistance;
    }
    
    // Path Compression Find
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        // Compress the path by pointing directly to the ultimate root
        return parent[i] = find(parent[i]); 
    }
    
    // Standard Union
    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ; // Merge the sets
        }
    }
}