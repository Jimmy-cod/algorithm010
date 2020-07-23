package Week07;

import java.util.ArrayList;

public class LC130_surrounded_regions {
/*
分区，连通的问题都适合用并查集解决。
思路：
1.所有边界上的'O'、内部与界上的'O'都连通到一个“虚假节点”
2.最后遍历一遍矩阵，不与“虚假节点”连通的点都置为'X'
* */
    int m,n;
    int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        m = board.length;
        n = board[0].length;
        UF uf = new UF(m*n +1);
        int dump = m*n;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (board[i][j] == 'O'){
                    //need to times the col which is "n"
                    int p = i*n +j;
                    if (!isBoard(i,j)){
                        for (int[] d :dir){
                            int row = i+d[0];
                            int col = j+d[1];
                            if (isValid(row,col) && board[row][col] == 'O'){
                                uf.union(p,row*n+col);
                            }
                        }
                    }
                    else {
                        //边缘的'O'连到dump上
                        uf.union(p,dump);
                    }
                }
            }
        }

        for (int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                //need to times the col which is "n"
                if (!uf.isConnected(i*n+j,dump)){
                    board[i][j] = 'X';
                }
            }
        }
    }
    public boolean isBoard(int r, int c){
        return (r==0 || r==m-1 || c==0 || c==n-1);
    }

    public boolean isValid(int r, int c){
        return (r>=0 && r<m && c>=0 || c<n);
    }

    class UF{
        int[] parent;
        public UF(int n){
            parent = new int[n];
            for (int i=0;i<n;i++){
                parent[i]=i;
            }
        }

        public int find(int p){
            while(p!=parent[p]){
                parent[p]=parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p,int q){
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            parent[rootQ] = rootP;
        }

        public boolean isConnected(int p, int q){
            return find(p) == find(q);
        }
    }
}
