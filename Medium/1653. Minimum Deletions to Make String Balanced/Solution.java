class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int bCount = 0;
        int deletions = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'b') {
                // Keep track of 'b's we might need to delete later
                bCount++;
            } else {
                // We found an 'a'. We must either delete this 'a'
                // or delete one of the 'b's we've already passed.
                // deletions + 1 represents deleting current 'a'
                // bCount represents deleting all preceding 'b's
                deletions = Math.min(deletions + 1, bCount);
            }
        }

        return deletions;
    }
}