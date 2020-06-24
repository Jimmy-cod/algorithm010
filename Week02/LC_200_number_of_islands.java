package Week02;

import java.util.Deque;
import java.util.LinkedList;

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
        if (!inArea(grid,r,c) || grid[r][c]!='1'){
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

    //BFS,广度优先
    int nr,nc;
    public int munsIslands_BFS(char[][] grid) {
        int count=0;
        if (grid == null || grid.length ==0 ){
            return 0;
        }
        nr = grid.length;
        nc = grid[0].length;
        for(int i=0;i<nr;i++)
            for(int j=0;j<nc;j++){
                if(grid[i][j]=='1'){
                    bfsFill(grid,i,j);
                    count++;
                }
            }
        return count;
    }

    private void bfsFill(char[][] grid,int r, int c){
        //mark has come
        grid[r][c] = '0';
        Deque<Integer> queue = new LinkedList<>();
        // 用一个数保存两个数字！！(因为c<nc)
        int code = r*nc +c;
        queue.addFirst(code);
        while (!queue.isEmpty()){
            code = queue.pollLast(); //can't use get last, as it won't delete la
            int x = code/nc;
            int y = code%nc;
            //search up, and mark it to '0'
            if (x>0 && grid[x-1][y] == '1'){
                //search deeper
                queue.addFirst((x-1)*nc+y);
                grid[x-1][y] = '0';
            }
            if(x<nr-1 && grid[x+1][y] == '1'){//down
                queue.addFirst((x+1)*nc+y);
                grid[x+1][y] = '0';
            }
            if(y>0 && grid[x][y-1] == '1'){//left
                queue.addFirst(x*nc+y-1);
                grid[x][y-1] = '0';
            }
            if(y<nc-1 && grid[x][y+1] == '1'){//right
                queue.addFirst(x*nc+y+1);
                grid[x][y+1] = '0';
            }
        }
    }
}
