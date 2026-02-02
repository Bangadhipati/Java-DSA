import java.util.*;

class Solution {
    private TreeMap<Integer, Integer> small = new TreeMap<>();
    private TreeMap<Integer, Integer> large = new TreeMap<>();
    private int smallSize = 0;
    private long smallSum = 0;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int m = k - 1; // We need to pick k-1 more elements
        
        // Initial window: indices 1 to dist + 1
        for (int i = 1; i <= dist + 1; i++) {
            add(nums[i], m);
        }

        long minCost = smallSum;

        // Slide the window
        for (int i = dist + 2; i < n; i++) {
            remove(nums[i - dist - 1], m);
            add(nums[i], m);
            minCost = Math.min(minCost, smallSum);
        }

        return nums[0] + minCost;
    }

    private void add(int val, int m) {
        small.put(val, small.getOrDefault(val, 0) + 1);
        smallSum += val;
        smallSize++;

        if (smallSize > m) {
            int maxSmall = small.lastKey();
            removeOne(small, maxSmall);
            smallSum -= maxSmall;
            smallSize--;
            large.put(maxSmall, large.getOrDefault(maxSmall, 0) + 1);
        }
    }

    private void remove(int val, int m) {
        if (small.containsKey(val)) {
            removeOne(small, val);
            smallSum -= val;
            smallSize--;
        } else {
            removeOne(large, val);
        }

        if (smallSize < m && !large.isEmpty()) {
            int minLarge = large.firstKey();
            removeOne(large, minLarge);
            small.put(minLarge, small.getOrDefault(minLarge, 0) + 1);
            smallSum += minLarge;
            smallSize++;
        }
    }

    private void removeOne(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }
}