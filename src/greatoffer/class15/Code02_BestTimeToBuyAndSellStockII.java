package greatoffer.class15;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-11 16:20
 */
public class Code02_BestTimeToBuyAndSellStockII {


    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
//        int[][] dp = new int[prices.length][2];
//        dp[0][0] = -prices[0];
//        for (int i = 1; i < prices.length; i++) {
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
//            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
//        }
//        return dp[prices.length - 1][1];
        int ans = 0;
        for(int i = 1; i < prices.length; i++){
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

}
