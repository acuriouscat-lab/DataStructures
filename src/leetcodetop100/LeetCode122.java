package leetcodetop100;

public class LeetCode122 {

    public int maxProfit(int[] prices) {

        int min = 0;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            res = Math.max(res, prices[i] - min);
        }
        return res;
    }

    public int maxProfit1(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int N = prices.length;
        int[][] dp = new int[N][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[N - 1][1];
    }

    public int maxProfit2(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int N = prices.length;
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < N; i++) {
            int tmp = buy;
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i]);
        }
        return sell;
    }


}
