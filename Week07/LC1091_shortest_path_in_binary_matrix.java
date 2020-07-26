package Week07;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC1091_shortest_path_in_binary_matrix {

    final static int[][] DIRECTION = {{0,1},{0,-1},{1,1},{-1,-1},{-1,1},{1,-1},{1,0},{-1,0}};
    //A* Search
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null | grid[0].length == 0) {
            return -1;
        }

        int n = grid.length;
        int[][] visited = new int[n][n];
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(new Node(0,0,n,n));

        int step =1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                Node node = queue.remove();
                int r = node.x;
                int c = node.y;
                for (int[] dir : DIRECTION){
                    int row = r+dir[0];
                    int col = c+dir[1];
                    if (row==n-1 && col==n-1){
                        return step+1;
                    }
                    if(row>=0 && row<n && col>=0&& col<n){
                        if (grid[row][col] == 0 && visited[r][c] ==0){
                            queue.add(new Node(row,col,n,n));
                        }
                    }
                }
                visited[r][c] = 1;
            }
            step++;
        }

        return -1;
    }

    class Node implements Comparable<Node>{
        int x;
        int y;
        int destX;
        int destY;

        private Node(int x, int y, int destX, int destY) {
            this.x = x;
            this.y = y;
            this.destX = destX;
            this.destY = destY;
        }

        @Override
        public int compareTo(Node node) {
            return Math.max(Math.abs(node.destX-node.x),Math.abs(node.destY-node.y));
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
