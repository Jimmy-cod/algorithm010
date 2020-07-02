package Week04;

public class LC122_best_time_to_buy_sell_stock_ii {
    //“贪心” 的地方在于，对于 “今天的股价 - 昨天的股价”,贪心算法的决策是：只加正数
    class Solution {
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            for (int i =1;i<prices.length;i++){
                int p = prices[i] - prices[i-1];
                if (p>0){
                    maxProfit += p;
                }
            }
            return maxProfit;
        }
    }
}
