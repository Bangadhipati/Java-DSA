import java.util.*;

class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] fact = new int[n + 1];
        fact[0] = 1;
        
        // Precompute factorials
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
            numbers.add(i);
        }
        
        k--; // Convert to 0-based index
        StringBuilder sb = new StringBuilder();
        
        for (int i = n; i >= 1; i--) {
            int index = k / fact[i - 1];
            sb.append(numbers.get(index));
            numbers.remove(index);
            k %= fact[i - 1];
        }
        
        return sb.toString();
    }
}
