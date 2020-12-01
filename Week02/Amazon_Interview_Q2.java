package Week02;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Amazon_Interview_Q2 {

    //找到'0'， 然后看前后左右要几次迭代才能发现'1'.
    //记录所有这些次数，return 最大值
    int nr,nc;
    int ans = -1;
    HashMap<String,Integer> map = new HashMap<String,Integer>();
    public int daysOfUpdate(int rows, int columns, char[][] grid) {
        if (rows <=0 || columns<=0) {
            return ans;
        }

        nr = rows;
        nc = columns;
//        LinkedList<Integer> list = new LinkedList<>();

        for (int i=0;i<rows;i++){
            for(int j = 0;j<columns;j++){

                if (grid[i][j] == '0'){
                    int res = bfs(grid,i,j);
//                    System.out.println("row="+i+";col="+j+";res="+res);
                    ans = Math.max(ans,bfs(grid,i,j));
                }
            }
        }

        return ans;
    }


    private int search (char[][] grid,int r, int c,int level){

        if (inArea(grid,r-level,c) && grid[r-level][c] == '1'){
            return level;
        }
        if (inArea(grid,r+level,c) && grid[r+level][c] == '1'){
            return level;
        }
        if (inArea(grid,r,c-level) && grid[r][c-level] == '1'){
            return level;
        }
        if (inArea(grid,r,c+level) && grid[r][c+level] == '1'){
            return level;
        }

        return 0;
    }

    private int bfs(char[][] grid,int r, int c){
     //   System.out.println("r="+r+";c="+c+";level="+level);

        if (!inArea(grid, r, c) ){
            return Integer.MAX_VALUE;
        }
        if ( findOne(grid,r,c)){
            return 1;
        }
        if ( grid[r][c] == '2'){
            return Integer.MAX_VALUE;
        }
    // missing a condition to terminate
        grid[r][c] = '2';

          int c2 = bfs(grid, r, c + 1);
          int r1 = bfs(grid, r - 1, c);

          int c1 = bfs(grid, r, c - 1);
          int r2 = bfs(grid, r + 1, c);
          // clean up
        grid[r][c] = '0';
          int minR = Math.min(r1, r2);
          int minC = Math.min(c1, c2);
          //        if (minR<0) {
          //            minR = 1;
          //        }
          //        if (minC<0) {
          //            minC = 1;
          //        }
          return Math.min(minC, minR) + 1;
    }
    private boolean findOne(char[][] grid,int r, int c){

        if (inArea(grid,r,c) && grid[r][c] == '1'){
            return true;
        }
        if (inArea(grid,r-1,c) && grid[r-1][c] == '1'){
            return true;
        }
        if (inArea(grid,r+1,c) && grid[r+1][c] == '1'){
            return true;
        }
        if (inArea(grid,r,c-1) && grid[r][c-1] == '1'){
            return true;
        }
        if (inArea(grid,r,c+1) && grid[r][c+1] == '1'){
            return true;
        }

        return false;
    }
    private boolean inArea(char[][] grid,int r, int c) {
        return 0<= r && r<nr && 0<=c && c<nc;
    }


}

class test{
    public static void main(String[] args) {
        Amazon_Interview_Q2 solution = new Amazon_Interview_Q2();

        int row =4,col =5;
        char[][] grid = new char[4][5];
//        grid[0]= new char[]{'0', '0', '0', '0', '0'};
//        grid[1]= new char[]{'0', '0', '0', '1', '0'};
//        grid[2]= new char[]{'0', '0', '0', '0', '1'};
//        grid[3]= new char[]{'0', '1', '0', '0', '0'};

//        char[][] grid = new char[4][5];
////        grid[0]= new char[]{'0', '0', '0', '0'};
////        grid[1]= new char[]{'0', '0', '0', '1'};
//
        grid[0]= new char[]{'0', '0', '0', '0', '0'};
        grid[1]= new char[]{'0', '0', '0', '0', '0'};
        grid[2]= new char[]{'0', '0', '0', '0', '0'};
        grid[3]= new char[]{'0', '0', '0', '0', '1'};

        int res = solution.daysOfUpdate(row,col,grid);
        System.out.println("res="+res);
    }
}
