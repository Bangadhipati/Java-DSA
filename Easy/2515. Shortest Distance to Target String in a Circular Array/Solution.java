class Solution {
    // Fixed the typo: closestTarget instead of closetTarget
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            // Only calculate distance if we found a matching word
            if (words[i].equals(target)) {
                // Route 1: Direct absolute distance
                int directDist = Math.abs(i - startIndex);
                
                // Route 2: Wrap-around boundary distance
                int wrapDist = n - directDist;
                
                // The shortest path to THIS specific occurrence
                int shortestToThisTarget = Math.min(directDist, wrapDist);
                
                // Keep track of the overall minimum
                minDistance = Math.min(minDistance, shortestToThisTarget);
            }
        }
        
        // If minDistance was never updated, the target doesn't exist in the array
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}