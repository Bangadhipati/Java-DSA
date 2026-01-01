class Solution {
    public int mySqrt(int x) {
        // Edge case: 0 doesn't work with division logic
        if (x == 0) return 0;

        int start = 1;
        int end = x;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // TRICK: instead of 'mid * mid == x', use 'mid == x / mid'
            // This prevents overflow without using 'long' type.
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                // If mid is small, we look to the right.
                // We don't need to store 'ans', just move 'start'.
                start = mid + 1;
            } else {
                // If mid is too big, move 'end'
                end = mid - 1;
            }
        }
        
        // When the loop breaks, 'end' will always be the rounded-down answer.
        return end;
    }
}