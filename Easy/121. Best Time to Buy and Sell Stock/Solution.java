class Solution {
    public int maxProfit(int[] prices) {
        // Initialize minPrice to the highest possible value
        int minPrice = Integer.MAX_VALUE;
        // Initialize maxProfit to 0 (since we don't have to buy if it results in a loss)
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            // 1. If we find a new minimum price, update minPrice
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } 
            // 2. Otherwise, calculate the profit if we sold today, 
            // and update maxProfit if it's strictly better than our previous best
            else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        
        return maxProfit;
    }
}