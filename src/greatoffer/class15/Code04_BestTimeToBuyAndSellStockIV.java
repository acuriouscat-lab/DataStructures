package greatoffer.class15;

import java.util.Arrays;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-11 16:36
 */
public class Code04_BestTimeToBuyAndSellStockIV {

    public int maxProfit(int k, int[] prices) {
        // 交易 k 次 的最大收益
        int[] sell = new int[k + 1];
        int[] buy = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        return sell[k];
    }


}
