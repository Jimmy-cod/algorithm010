package Week06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC120_triangle {
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();
        int m = triangle.get(n-1).size();

        int[][] dp = new int[n+1][m+1];
        for (int i=n-1;i>=0;i--){
            //for (int j=0;j<triangle.get(i).size();j++){
            for (int j=triangle.get(i).size()-1;j>=0;j--){
                dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

/*
* 可以用一维数组记录中间值；
* 状态转移方程：f[i][j] = min(f[i - 1][j - 1], f[i - 1][j]) + triangle[i][j];
* */
    public int minimumTotal_2(List<List<Integer>> triangle) {

        int n = triangle.size();
        int m = triangle.get(n-1).size();

        int[] dp = new int[m+1];
        for (int i=n-1;i>=0;i--){
            //for (int j=0;j<triangle.get(i).size();j++){
            for (int j=triangle.get(i).size()-1;j>=0;j--){
                dp[j]=Math.min(dp[j],dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
