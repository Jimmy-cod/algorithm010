package Week08;

import java.util.HashSet;
import java.util.Set;

public class LC52_n_queens_ii {
    static class Solution {

        //位运算,最多能是n=32个皇后
/*
1.通过位运算，得到长度为n的bits，可以放queen位置为1，其他为0
2.历遍bits(while bits>0)
3.得到最低位的1，bits & -bits;
4.下探下一行，要把最低位的1放入记录值(row+1,(cols|p),((master|p)*2),((slaver|p)/2),n)
5.把bits中的最低位的1去掉。
* */
        int res =0;
        public int totalNQueens(int n) {
            dfs(n,0,0,0,0);
            return res;
        }

        private void dfs(int n, int row, int col, int master, int slaver) {
            if (row == n){
                res++;
                return ;
            }

            System.out.println("col : "+Integer.toBinaryString(col)+
                    " ;master : "+Integer.toBinaryString(master)+
                    " ;slaver : "+Integer.toBinaryString(slaver));
            /*
            //位运算, 将所有能放置 Q 的位置由 0 变成 1
            //或运算|：相同位只要一个为1即为1，col | master | slaver得到所有皇后位置(32位）
            int or = col | master | slaver;
            //not运算 ~ ：0和1全部取反。得到可以放queen位置
            int notOr = ~or;
            //System.out.println("notOr:"+Integer.toBinaryString(notOr));
            //(1 << n) -1拿到所有位置为1的bits
            int ttl = (1 << n) -1;
            //System.out.println(Integer.toBinaryString(ttl));
            //and运算 &:同位的两个数字都为1，则为1；若有一个不为1，则为0。得到长度为n的bits位置, queen位置为1，其他为0
            int bits = notOr & ttl;
            */
            int bits = ~(col | master | slaver) & ((1 << n) -1);
            System.out.println("bits:"+Integer.toBinaryString(bits));
            while (bits > 0){
                int pick = bits & -bits; //得到最低位的1
                System.out.println("pick : "+Integer.toBinaryString(pick));
                //<<1左移一位(乘2)，因为master是要往左下角走一位，>>1右移一位（除2），slaver是往右下角移一位
                dfs(n,row+1,col | pick, (master | pick) <<1, (slaver | pick) >> 1);
                //清零最低位的1
                bits &= bits -1;
            }
        }


        public int totalNQueens_2(int n) {

            if (n<1) return 0;
            Set<Integer> cols = new HashSet<>();
            Set<Integer> master = new HashSet<>();
            Set<Integer> slaver = new HashSet<>();
            backtrace(0,cols,master,slaver,n);
            return res;
        }

        private void backtrace(int row, Set<Integer> cols, Set<Integer> master, Set<Integer> slaver,int n) {
            if (row == n){
                res++;
                return ;
            }
            for (int i=0;i<n;i++){
                if (!cols.contains(i)
                        && !master.contains(i+row)
                        && !slaver.contains((i-row))
                ){
                    //可以放，记录位置，下探到下一行
                    cols.add(i);
                    master.add(i+row);
                    slaver.add(i-row);
                    //下探到下一行
                    backtrace(row+1,cols,master,slaver,n);

                    cols.remove(i);
                    master.remove(i+row);
                    slaver.remove(i-row);
                }
            }
//            不能有return,必须等到所有行都差完。
//            return;
        }
    }

    public static void main(String[] args) {
        Solution solution  = new Solution();

        int res = solution.totalNQueens(4);
        System.out.println("result:"+res);

    }
}
