package leetcodetop100;

public class LeetCode714 {

    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        int[][] dp = new int[N][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[N - 1][1];
    }

    public int maxProfit1(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        int buy = prices[0] + fee;
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (prices[i] > buy) {
                ans += prices[i] - buy;
                buy = prices[i];
            } else if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            }
        }
        return  ans;
    }


}
