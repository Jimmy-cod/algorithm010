package Week08;

import java.util.ArrayList;
import java.util.List;

public class LC51_n_queens {

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
}
