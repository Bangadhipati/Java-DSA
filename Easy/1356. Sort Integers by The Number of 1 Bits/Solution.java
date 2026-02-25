import java.util.Arrays;

class Solution {
    public int[] sortByBits(int[] arr) {
        // 1. Encode the bit count and original value into a single integer
        for (int i = 0; i < arr.length; i++) {
            int bitCount = Integer.bitCount(arr[i]);
            
            // Shift bitCount to the upper 16 bits and append the original value
            arr[i] = (bitCount << 16) | arr[i];
        }
        
        // 2. Sort the array. 
        // The upper 16 bits (bit count) will dominate the sort. 
        // If they are equal, the lower 16 bits (original value) will break the tie.
        Arrays.sort(arr);
        
        // 3. Decode the array back to original values
        for (int i = 0; i < arr.length; i++) {
            // 0xFFFF is the hex mask for the lower 16 bits (1111111111111111 in binary)
            arr[i] = arr[i] & 0xFFFF;
        }
        
        return arr;
    }
}