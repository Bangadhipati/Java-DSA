class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int maxDist = 0;
        
        while (i < nums1.length && j < nums2.length) {
            // If the pair is valid, calculate the distance and try to stretch 'j' further
            if (nums1[i] <= nums2[j]) {
                // If i > j somehow, j - i is negative, so maxDist safely ignores it
                maxDist = Math.max(maxDist, j - i);
                j++;
            } 
            // If the pair is invalid, 'nums1[i]' is too large. Move 'i' to find a smaller number.
            else {
                i++;
            }
        }
        
        return maxDist;
    }
}