import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;
        
        // Phase 1: Find the top two potential candidates
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                // A completely different number reduces both candidate counts
                count1--;
                count2--;
            }
        }
        
        // Phase 2: Verify if the candidates actually meet the threshold
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }
        
        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3;
        
        if (count1 > threshold) result.add(candidate1);
        if (count2 > threshold) result.add(candidate2);
        
        return result;
    }
}