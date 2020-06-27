package Week02;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Amazon_Interview {

    //找到'0'， 然后看前后左右要几次迭代才能发现'1'.
    //记录所有这些次数，return 最大值
    int nr,nc;
    int ans = -1;
    HashMap<String,Integer> map = new HashMap<String,Integer>();
    public int daysOfUpdate(int rows, int columns, char[][] grid) {

        nr = rows;
        nc = columns;
        LinkedList<Integer> list = new LinkedList<>();

        for (int i=0;i<rows;i++){
            for(int j = 0;j<columns;j++){

                if (grid[i][j] == '0'){
                    bfs(grid,i,j,0);
                }
            }
        }

        return ans;
    }

//    private void bfsFill(char[][] grid,int r, int c){
//        //mark has come
//        grid[r][c] = '2';
//        Deque<Integer> queue = new LinkedList<>();
//        // 用一个数保存两个数字！！(因为c<nc)
//        int code = r*nc +c;
//        queue.addFirst(code);
//        while (!queue.isEmpty()){
//            code = queue.pollLast(); //can't use get last, as it won't delete la
//            int x = code/nc;
//            int y = code%nc;
//            //search up, and mark it to '0'
//            if (x>0 && grid[x-1][y] == '1'){
//                //search deeper
//                queue.addFirst((x-1)*nc+y);
//                grid[x-1][y] = '0';
//            }
//            else if(x<nr-1 && grid[x+1][y] == '1'){
//                grid[x+1][y] = '0';
//            }
//            else if(y>0 && grid[x][y-1] == '1'){
//                grid[x][y-1] = '0';
//            }
//            else if(y<nc-1 && grid[x][y+1] == '1'){
//                grid[x][y+1] = '0';
//            }
//            else {
//                //search deeper
//                queue.addFirst((x-1)*nc+y);
//                //down
//                queue.addFirst((x+1)*nc+y);
//                //left
//                queue.addFirst(x*nc+y-1);
//                //right
//                queue.addFirst(x*nc+y+1);
//
//            }
//        }
//    }
//


    private boolean bfs(char[][] grid,int r, int c, int level){
        System.out.println("r="+r+";c="+c+";level="+level);

        if (!inArea(grid, r, c) ){
            return false;
        }
        if ( findOne(grid,r,c)){
            String key = String.valueOf(r) +"-"+String.valueOf(c);
            map.put(key,level);
            return true;
        }
        if ( grid[r][c] == '2'){
            return false;
        }

        System.out.println("mark to '2' r="+r+";c="+c+";level="+level+";grid[r][c]="+grid[r][c]);
//        if (grid[r][c] == '2'){
//            return;
//        }

//        if (grid[r][c] != '1' ){
//            if (ans <=level) ans = level+1;
//            return;
//        }

        grid[r][c] = '2';
        boolean r1 = bfs(grid, r - 1, c, level+1);
        boolean r2 = bfs(grid, r + 1, c, level+1);
        boolean c1 = bfs(grid, r, c - 1, level+1 );
        boolean c2 = bfs(grid, r, c + 1, level+1);

        System.out.println("mark back to '0' r="+r+";c="+c+";level="+level+";grid[r][c]="+grid[r][c]);
        grid[r][c] = '0';

        return r1 && r2 && c1 && c2;
    }
    private boolean findOne(char[][] grid,int r, int c){
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
        Amazon_Interview solution = new Amazon_Interview();

        char[][] grid = new char[4][5];
        grid[0]= new char[]{'0', '1', '1', '0', '1'};
        grid[1]= new char[]{'0', '1', '0', '1', '0'};
        grid[2]= new char[]{'0', '0', '0', '0', '1'};
        grid[3]= new char[]{'0', '1', '0', '0', '0'};

        int res = solution.daysOfUpdate(4,5,grid);
        System.out.println("res="+res);
    }
}
