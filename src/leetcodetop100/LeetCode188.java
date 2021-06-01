package leetcodetop100;

public class LeetCode188 {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        k = Math.min(k, N / 2);

        int[][] buy = new int[N][k + 1];
        int[][] sell = new int[N][k + 1];
        for(int i = 0; i <= k; i++){
            buy[0][i] = -prices[0];
        }
        int ans = 0;
        for (int i = 1; i < N; i++) {
            buy[i][0] = Math.max(buy[i - 1][0], -prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
                ans = Math.max(ans, sell[i][j]);
            }
        }
        return ans;
    }

}
