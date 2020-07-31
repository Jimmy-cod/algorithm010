package Week03;

import Week08.LC52_n_queens_ii;

import java.util.*;

public class LC51_n_queens {
    static class Solution {
/*
* Bitwise Operators
1.通过位运算，得到长度为n的bits，可以放queen位置为1，其他为0
2.历遍bits(while bits>0)
3.得到最低位的1，bits & -bits;
4.下探下一行，要把最低位的1放入记录值(row+1,(cols|p),((master|p)*2),((slaver|p)/2),n)
5.把bits中的最低位的1去掉。
* */

        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n <= 0) {
                return res;
            }
            List<Integer> bitRes = new ArrayList<>();

            dfs(n, 0, 0, 0, 0, bitRes, res);
            return res;
        }

        private void dfs(int n, int row, int col, int master, int slaver, List<Integer> bitRes, List<List<String>> res) {
            if (row == n) {
                List<String> board = convert(bitRes, n);
                res.add(board);
                return;
            }
            int bits = ~(col | master | slaver) & ((1 << n) - 1);
            while (bits > 0) {
                int p = bits & -bits; //得到最低位的1
                bitRes.add(p);
                dfs(n, row + 1, col | p, (master | p) << 1, (slaver | p) >> 1, bitRes, res);
                bitRes.remove(bitRes.size() - 1);
                bits = bits & (bits - 1);
            }
        }

        private List<String> convert(List<Integer> bitRes, int n) {
            List<String> res = new ArrayList<>();
            for (int bitIdx : bitRes) {
                StringBuilder sb = new StringBuilder();
                int count = 0;
                //like '0100',move 3 time, the the '1' position.
                while (bitIdx > 0) {
                    bitIdx >>= 1;
                    count++;
                }
                for (int j = 0; j < n; j++) {
                    //as bitwise start with position 1, array start with 0.
                    if (j == count-1) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                res.add(sb.toString());
            }
            return res;
        }

        //检测主对角线或者副对角线，就是如果row+col值相同说明在相同主对角线上，row-col值相同，说明在同一副对角线上
        public List<List<String>> solveNQueens_2(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n <= 0) {
                return res;
            }
            Set<Integer> cols = new HashSet<>();
            Set<Integer> master = new HashSet<>();
            Set<Integer> slaver = new HashSet<>();
            Stack<Integer> temp = new Stack<>();
            backtrack(n, cols, master, slaver, temp, 0, res);
            return res;
        }

        private void backtrack(int n, Set<Integer> cols, Set<Integer> master, Set<Integer> slaver,
                               Stack<Integer> stack, int row, List<List<String>> res) {
            if (row == n) {
                List<String> board = convert2board(stack, n);
                res.add(board);
                return;
            }
            // 针对每一列，尝试是否可以放置
            for (int i = 0; i < n; i++) {
                if (!cols.contains(i)
                        //row+col值相同说明在相同主对角线上
                        && !master.contains(i + row)
                        //row-col值相同，说明在同一副对角线上
                        && !slaver.contains(i - row)
                ) {
                    //记录queen位置
                    stack.push(i);
                    cols.add(i);
                    master.add(i + row);
                    slaver.add(i - row);
                    backtrack(n, cols, master, slaver, stack, row + 1, res);

                    //回朔
                    stack.pop();
                    cols.remove(i);
                    master.remove(i + row);
                    slaver.remove(i - row);
                }
            }

        }

        private List<String> convert2board(Stack<Integer> stack, int n) {
            List<String> board = new LinkedList<>();
            for (Integer num : stack) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (i == num) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                board.add(sb.toString());
            }
            return board;
        }
    }

    public static void main(String[] args) {
        Solution solution  = new Solution();
        List<List<String>> res = solution.solveNQueens(4);
        System.out.println("result:"+res);

    }
}
