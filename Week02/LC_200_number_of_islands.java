package Week02;

public class LC_200_number_of_islands {
/*
* 「陆地沉没方法」，即遍历完一个陆地格子就让陆地「沉没」为海洋。
* 这种方法看似很巧妙，但实际上有很大隐患，因为这样我们就无法区分「海洋格子」和「已遍历过的陆地格子」了。
* 如果题目更复杂一点，这很容易出 bug。
* */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length ==0 ){
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int ans = 0;
        for (int i=0;i<nr;i++){
            for(int j = 0;j<nc;j++){
                if (grid[i][j] == '1'){
                    ans++;
                    dfs(grid,i,j);
                }
            }
        }
        return ans;
    }

    //用DFS 把找到的岛屿遍历一次
    private void dfs(char[][] grid,int r, int c){
        if (!inArea(grid,r,c)){
            return;
        }
        if (grid[r][c]!='1'){
            return;
        }

        grid[r][c] = '2';
        dfs(grid,r-1,c);
        dfs(grid,r+1,c);
        dfs(grid,r,c-1);
        dfs(grid,r,c+1);

    }

    private boolean inArea(char[][] grid, int r, int c) {
        return 0<= r && r<grid.length && 0<=c && c<grid[0].length;
    }
}
