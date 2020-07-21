package Week07;

public class LC200_number_of_islands {
/*
并查集
 最终岛屿的数量就是并查集中连通分量的数目。
1. 把整个grid传入UnionFind计算count
2. 二维矩阵m*n,在一维数组的位置是：（第几行×矩阵宽度）+ 在第几列
3. 历遍grid时，前面已经执行过，不用往回查

 */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 ){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i< m; i++){
            for(int j = 0; j< n; j++){
                if (grid[i][j] == '1') {
                    //二维矩阵m*n,在一维数组的位置是：（第几行×矩阵宽度）+ 在第几列
                    //前面已经执行过，不用往回查
//                    if (i - 1 >= 0 && grid[i-1][j] == '1') {
//                        uf.union(i * n + j, (i-1) * n + j);
//                    }
                    if (i + 1 < m && grid[i+1][j] == '1') {
                        uf.union(i * n + j, (i+1) * n + j);
                    }
//                    if (j - 1 >= 0 && grid[i][j-1] == '1') {
//                        uf.union(i * n + j, i * n + j - 1);
//                    }
                    if (j + 1 < n && grid[i][j+1] == '1') {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        return uf.count;
    }

    class UnionFind{
        int[] parent;
        int count;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        //二维变一维
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                }
            }
        }

        public int find(int p){
            while(p!= parent[p]){
                //压缩路径
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            parent[rootP] = rootQ;
            count--;
        }
    }
}
