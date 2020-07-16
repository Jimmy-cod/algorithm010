package Week06;
/*
动态规划，无非就是利用历史记录，来避免我们的重复计算。而这些历史记录，我们得需要一些变量来保存，一般是用一维数组或者二维数组来保存。
第一步骤：定义数组元素的含义
第二步骤：找出数组元素之间的关系式,最优子结构，把大的问题拆分成小的问题
第三步骤：找出初始值
优化: 当我们要填充第三行的值的时候，我们需要用到第一行的值吗？答是不需要的
用一行保存中间值就行了
* */
public class LC62_unique_paths {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] =1;
        for (int i=0;i<m;i++){
            for (int j=1;j<n;j++){
               dp[j] = dp[j] +dp[j-1];
            }
        }
        return dp[n-1];
    }
}
