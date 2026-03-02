class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];
        
        // 1. Calculate the number of trailing zeros for each row
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    count++;
                } else {
                    break;
                }
            }
            zeros[i] = count;
        }
        
        int totalSwaps = 0;
        
        // 2. Greedily find and bubble up the required row
        for (int i = 0; i < n; i++) {
            int targetZeros = n - 1 - i;
            int j = i;
            
            // Find the closest row 'j' that has enough trailing zeros
            while (j < n && zeros[j] < targetZeros) {
                j++;
            }
            
            // If no such row exists, the grid cannot be made valid
            if (j == n) {
                return -1;
            }
            
            // The number of swaps needed to bring row 'j' to position 'i'
            totalSwaps += (j - i);
            
            // Simulate the swaps by bubbling the values down
            // We shift everything from i to j-1 down by one spot
            int temp = zeros[j];
            while (j > i) {
                zeros[j] = zeros[j - 1];
                j--;
            }
            zeros[i] = temp;
        }
        
        return totalSwaps;
    }
}