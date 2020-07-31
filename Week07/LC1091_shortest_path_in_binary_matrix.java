package Week07;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC1091_shortest_path_in_binary_matrix {
/*
if (grid[x][y] != 0 && grid[x][y] <= step + 1) continue; 是最重要的一行代码，相当于 grid[x][y] == 0
（说明没走过 且 可以走）或者 当前位置记录的步数大于前一步加一（说明之前走过这里 且 现在走比之前走更近一些），才执行加入到优先队列的操作。
* */
    final static int[][] DIRECTION = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, 1}, {-1, -1}, {1, -1}, {1, 1}
    };
    //A* Search
    int n;
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null | grid[0].length == 0) {
            return -1;
        }
        n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        if (n==1) return 1;
        Queue<Node> queue = new PriorityQueue<Node>();
        queue.offer(new Node(0,0,1));
        grid[0][0] = 1;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            int step = grid[node.x][node.y];
            for (int[] dir : DIRECTION){
                int x = node.x+dir[0];
                int y = node.y+dir[1];
                if (x==n-1 && y==n-1){
                    return step+1;
                }
                if (x < 0 || x >= n || y < 0 || y >= n) continue;
                if (grid[x][y] != 0 && grid[x][y] <= step + 1) continue;

                grid[x][y] = step +1;
                queue.offer(new Node(x,y,step +1));
            }
        }
        return -1;
    }

    class Node implements Comparable<Node>{
        int x;
        int y;
        int f;

        private Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            int distance = Math.max(n - 1 - x, n - 1 - y);
            this.f = distance + step;
        }

        @Override
        public int compareTo(Node node) {
            return this.f - node.f;
        }
    }

    //BFS
    public int shortestPathBinaryMatrix_2(int[][] grid) {
        if (grid==null|grid[0].length == 0){
            return -1;
        }
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        if (n==1) return 1;
        int[][] visited = new int[n][n];
        Queue<Pair<Integer,Integer>> queue = new LinkedList<>();
        queue.add(new Pair<Integer,Integer>(0,0));
        int step =1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                Pair<Integer,Integer> pair = queue.remove();
                int r = pair.getKey();
                int c = pair.getValue();
                for (int[] dir : DIRECTION){
                    int row = r+dir[0];
                    int col = c+dir[1];
                    if (row==n-1 && col==n-1){
                        return step+1;
                    }
                    if(row>=0 && row<n && col>=0&& col<n){
                        if (grid[row][col] == 0 && visited[r][c] ==0){
                            queue.add(new Pair<Integer,Integer>(row,col));
                        }
                    }
                }
                visited[r][c] = 1;
            }
            step++;
        }
        return -1;
    }


}
