class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        // Find the fastest worker to set a tight upper bound for the binary search
        long minT = workerTimes[0];
        for (int t : workerTimes) {
            if (t < minT) minT = t;
        }
        
        long low = 1;
        long high = minT * (long) mountainHeight * (mountainHeight + 1) / 2;
        long bestTime = high;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (canFinishInTime(mid, mountainHeight, workerTimes)) {
                bestTime = mid;   // This time works, record it
                high = mid - 1;   // Try to find an even smaller time
            } else {
                low = mid + 1;    // Time is too short, we need more seconds
            }
        }
        
        return bestTime;
    }
    
    private boolean canFinishInTime(long timeLimit, int targetHeight, int[] workerTimes) {
        long totalHeightReduced = 0;
        
        for (int t : workerTimes) {
            // We want to solve for maximum x where x(x+1) <= (2 * timeLimit) / t
            long k = (timeLimit * 2) / t; 
            
            // Fast approximation of x using integer square root
            long x = (long) Math.sqrt(k);
            
            // Fine-tune x to eliminate any floating-point approximation errors
            while (x * (x + 1) > k) {
                x--;
            }
            while ((x + 1) * (x + 2) <= k) {
                x++;
            }
            
            totalHeightReduced += x;
            
            // Early exit: if we already reduced enough height, this timeLimit is valid
            if (totalHeightReduced >= targetHeight) {
                return true;
            }
        }
        
        return false;
    }
}