class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        
        // Efficiency: If target is out of bounds, return first letter immediately
        if (target >= letters[n - 1]) {
            return letters[0];
        }

        int low = 0;
        int high = n - 1;

        while (low < high) {
            // Using bitwise shift for faster division by 2
            int mid = (low + high) >>> 1;

            if (letters[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return letters[low];
    }
}