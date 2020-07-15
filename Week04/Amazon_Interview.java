package Week04;

import javafx.util.Pair;

import java.util.*;

public class Amazon_Interview {
/*
* 历遍矩阵，找到0时，广度优先，一层层扩展（水波纹），找到最近的‘1’
* 最后输出最大的
* */
    int nr,nc;
    public int daysOfUpdate(int rows, int columns, char[][] grid) {
        int ans = -1;
        if (rows <=0 || columns<=0) {
            return ans;
        }
        nr = rows;
        nc = columns;
        for (int i=0;i<rows;i++){
            for(int j = 0;j<columns;j++){
                if (grid[i][j] == '0'){
                    int res = bfs(grid,i,j);
                    ans = Math.max(ans,res);
                }
            }
        }
        return ans;
    }

    private int bfs(char[][] grid,int row, int col){
        int level = 0;
        int x,y;
        Queue<Pair<Integer,Integer>> queue = new ArrayDeque<>();
        Set<Pair<Integer,Integer>> visited = new HashSet<>();
        addQueue(row-1,col,queue);
        addQueue(row+1,col,queue);
        addQueue(row,col-1,queue);
        addQueue(row,col+1,queue);
        while(!queue.isEmpty()){
            level++;
            int size = queue.size();
            for (int i =0;i<size;i++){
                Pair<Integer,Integer> key = queue.poll();
                if (!visited.contains(key)){
                    x = key.getKey();
                    y = key.getValue();

                    if (grid[x][y] == '1') {
                        return level;
                    }
                    visited.add(key);
                    addQueue(x-1,y,queue);
                    addQueue(x+1,y,queue);
                    addQueue(x,y-1,queue);
                    addQueue(x,y+1,queue);
                }
            }
        }
        return 0;
    }

    private void addQueue(int r, int c, Queue<Pair<Integer,Integer>> queue) {
        if (0<= r && r<nr && 0<=c && c<nc){
            queue.add(new Pair<>(r,c));
        }
    }
}

class test{
    public static void main(String[] args) {
        Amazon_Interview solution = new Amazon_Interview();

        int row =4,col =5;
        char[][] grid = new char[4][5];
//        grid[0]= new char[]{'0', '1', '1', '0', '1'};
//        grid[1]= new char[]{'0', '1', '0', '1', '0'};
//        grid[2]= new char[]{'0', '0', '0', '0', '1'};
//        grid[3]= new char[]{'0', '1', '0', '0', '0'};

//        int row =2,col =4;
//        char[][] grid = new char[2][4];
//        grid[0]= new char[]{'0', '0', '0', '0'};
//        grid[1]= new char[]{'0', '0', '0', '1'};

        grid[0]= new char[]{'0', '0', '0', '0', '0'};
        grid[1]= new char[]{'0', '0', '0', '0', '0'};
        grid[2]= new char[]{'0', '0', '0', '0', '0'};
        grid[3]= new char[]{'0', '0', '0', '0', '1'};

        int res = solution.daysOfUpdate(row,col,grid);
        System.out.println("res="+res);
    }
}
