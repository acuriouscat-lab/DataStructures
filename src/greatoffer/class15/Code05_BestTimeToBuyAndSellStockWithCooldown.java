package greatoffer.class15;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-11 16:39
 */
public class Code05_BestTimeToBuyAndSellStockWithCooldown {
    //leetcode 309
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int N = prices.length;
        int[] sell = new int[N];
        int[] buy = new int[N];
        buy[0] = -prices[0];
        buy[1] = Math.max(-prices[0],-prices[1]);
        sell[1] = Math.max(0,prices[1] - prices[0]);
        for(int i = 2; i < prices.length; i++){
            buy[i] = Math.max(buy[i - 1],sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1],buy[i - 1] + prices[i]);
        }
        return sell[N - 1];
    }
}
