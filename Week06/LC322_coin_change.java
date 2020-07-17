package Week06;

import java.util.*;

public class LC322_coin_change {

/*
* 动态规划：自下而上
a.分治（子问题）:前面能转移过来的状态的最小值加上枚举的硬币数量 1
b.状态数组定义 : 定义 F(i) 为组成金额 i 所需最少的硬币数量
c.DP方程: F(i)=min(F(i−1),F(i−2),F(i−5))+1
初始值：F(0)=0
* */
    public int coinChange(int[] coins, int amount) {
        int a = amount+1;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,a);
        dp[0] =0;

        for (int remainAmt=1;remainAmt<=amount;remainAmt++){
            for(int c :coins){
                if (c<=remainAmt){
                    dp[remainAmt] = Math.min(dp[remainAmt],dp[remainAmt-c]+1);
                }
            }
        }
        return dp[amount]==a ? -1: dp[amount];
    }
/*
* BFS:找到叶节点为0的最短路径
* 超出内存限制,这也是一种暴力递归
* */
    public int coinChange_BFS(int[] coins, int amount) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(amount);
        int level =0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                Integer amt = queue.poll();

                if (amt == 0){
                    return level;
                }
                else if (amt <0){
                    continue;
                }
                else{
                    for (Integer c : coins){
                        queue.offer(amt-c);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}

class Test36{
    public static void main(String[] args) {
        LC322_coin_change s = new LC322_coin_change();
        int[] coins = {1,2,5};
        s.coinChange(coins,100);
    }
}
