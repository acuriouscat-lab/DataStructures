package greatoffer.class15;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-11 16:56
 */
public class Code06_BestTimeToBuyAndSellStockWithTransactionFee {

    //leetcode 714
    public int maxProfit(int[] prices, int fee) {
        int N = prices.length;
        int[][] dp = new int[N][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[N - 1][1];
    }

}
