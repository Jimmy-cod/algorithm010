package Week06;

public class LC63_unique_paths_ii {
    //加一个长度，判断前一数不是障碍时，计算dp
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length==0){
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m+1][n+1];
        dp[0][1]=1;
        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                if (obstacleGrid[i-1][j-1] == 0){
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }

    /*必须画图加深理解
    * 遇到1时，此路不通，dp =0
    * */
    public int uniquePathsWithObstacles_2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i][j - 1] == 1) {
                    dp[j] = 0;
                } else {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n];
    }
}
