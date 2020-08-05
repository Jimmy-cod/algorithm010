package Week09;

public class LC_stock_questions {
/*
https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/

第一维：i表示天数，
第二维：K 为最多交易数
第三维：数字0和1：1 表示持有，0 表示没有持有

dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
              max(   选择 rest  ,             选择 sell      )
解释：今天我没有持有股票，有两种可能：
要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。

dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
              max(   选择 rest  ,           选择 buy         )
解释：今天我持有着股票，有两种可能：
要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了

base case：
dp[-1][k][0] = dp[i][0][0] = 0
dp[-1][k][1] = dp[i][0][1] = -infinity

状态转移方程：
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

最后return dp没有持有状态：dp[n - 1][k][0];
*/
    //121: //k=1最多交易1次
    public int maxProfit_121(int[] prices) {
        if (prices == null || prices.length==0) return 0;
        //k=1最多交易1次
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] =0;
        dp[0][1] =-prices[0];

        for (int i = 1;i<n;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
        }
        //return最后一天没有股票状态
        return dp[n-1][0];
    }

    int maxProfit_k_1(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    //122: k为正无穷
    /* 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的
    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
            = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])

    我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
    dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
    dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     */

    public int maxProfit_122(int[] prices) {
        if (prices == null || prices.length==0) return 0;
        //k=1最多交易1次
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] =0;
        dp[0][1] =-prices[0];

        for (int i = 1;i<n;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[n-1][0];
    }



    //k = 2,要求穷举k所有状态
//    int max_k = 2;
//    int[][][] dp = new int[n][max_k + 1][2];
//    for (int i = 0; i < n; i++) {
//            for (int k = max_k; k >= 1; k--) {
//                if (i - 1 == -1) { /*处理 base case */ }
//                dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
//                dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
//            }
//        }
//    // 穷举了 n × max_k × 2 个状态，正确。
//    return dp[n - 1][max_k][0];

    public int maxProfit_123(int[] prices) {
        if (prices == null || prices.length==0) return 0;
        int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    /*处理 base case */
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }

//穷举k=2
//    dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
//    dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
//    dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
//    dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
    int maxProfit_123_k_2(int[] prices) {
        if (prices == null || prices.length==0) return 0;
        int n = prices.length;

        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }

/* 188: k = any integer
这题应该和上一题的第一个解法没啥区别。但是出现了一个超内存的错误，原来是传入的 k 值会非常大，dp 数组太大了。现在想想，交易次数 k 最多有多大呢？
一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k = +infinity。这种情况是之前解决过的。
*/

    public int maxProfit_188(int k,int[] prices) {
        if (prices == null || prices.length==0) return 0;
        int n = prices.length;
        if (k>n/2){
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int temp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
            }
            return dp_i_0;
        }
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                if (i - 1 == -1) {
                    /*处理 base case */
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

    //309: k = +infinity with cooldown
    //    dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
    //    dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
    //    解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
    public int maxProfit_309(int[] prices) {
        if (prices == null || prices.length==0) return 0;
        //k=1最多交易1次
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0;i<n;i++){
            if (i - 1 == -1) {
                /*处理 base case */
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            if (i == 1) {
                /*处理 base case */
                dp[1][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-2][0]-prices[i]);
        }
        return dp[n-1][0];
    }

    //714
    //dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
    //dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
    //解释：相当于买入股票的价格升高了。
    //在第一个式子里减也是一样的，相当于卖出股票的价格减小了。

    public int maxProfit_714(int[] prices, int fee) {

        if (prices == null || prices.length==0) return 0;
        //k=1最多交易1次
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0;i<n;i++){
            if (i ==0) {
                /*处理 base case */
                dp[i][0] = 0;
                dp[i][1] = -prices[i]-fee;
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]-fee);
        }
        return dp[n-1][0];
    }
}
