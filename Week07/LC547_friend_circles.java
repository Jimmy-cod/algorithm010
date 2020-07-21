package Week07;

public class LC547_friend_circles {
/*
UnionFind-并查集
判断是否可以用并查集，类似有几个朋友圈，岛屿，任意两个是否相连
构造函数初始化数据结构需要 O(N) 的时间和空间复杂度
每次find都会进行路径压缩,使查找变为O(1)
* */
    public int findCircleNum(int[][] M) {
        int n =  M.length;
        if(M == null || n ==0) return 0;
        UF uf = new UF(n);
        for (int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                if (M[i][j] == 1){
                    uf.union(i,j);
                }
            }
        }
        return uf.count;
    }

    class UF {
        private int[] parent;
        private int count;
        public UF(int n){
            parent = new int[n];
            count = n;
            for (int i =0;i<n;i++){
                parent[i] = i;
            }
        }

        public int find(int p){
            while(p != parent[p]){
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
        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }
    }
}
