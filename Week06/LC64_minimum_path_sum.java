package Week06;

public class LC64_minimum_path_sum {
/*
* 动态规划:
* 只能向右或向下走，换句话说，当前单元格 (i,j)(i,j) 只能从左方单元格 (i-1,j)(i−1,j) 或上方单元格 (i,j-1)(i,j−1) 走到，
* 因此只需要考虑矩阵左边界和上边界
* 当左边和上边都不是矩阵边界时： 即当 grid[i][j] = Math.min(grid[i][j-1],grid[i-1][j]) + grid[i][j];
* 当只有左边是矩阵边界时： 只能从上面来: grid[i][j] += grid[i][j-1];
* 当只有上边是矩阵边界时： 只能从左面来: grid[i][j] += grid[i-1][j];
* 当左边和上边都是矩阵边界时： 即当i = 0, j = 0i=0,j=0时，其实就是起点， dp[i][j] = grid[i][j]
* */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid[0].length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;

        for (int i=0;i<m;i++){
            for (int j =0;j<n;j++){
                if (i==0 && j==0){
                    continue;
                }
                if (i == 0){
                    grid[i][j] += grid[i][j-1];
                }
                else if (j == 0) {
                    grid[i][j] += grid[i-1][j];
                }
                else {
                    grid[i][j] = Math.min(grid[i][j-1],grid[i-1][j]) + grid[i][j];
                }
            }
        }
        return grid[m-1][n-1];
    }
}
