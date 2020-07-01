package Week03;

import java.util.*;

public class LC51_n_queens {

    //检测主对角线或者副对角线，就是如果row+col值相同说明在相同主对角线上，row-col值相同，说明在同一副对角线上
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        int[] queens = new int[n];
        for (int i=0;i<n;i++){
            queens[i] = i;
        }
        Set<Integer> cols = new HashSet<>();
        Set<Integer> master  = new HashSet<>();
        Set<Integer> slaver  = new HashSet<>();
        Stack<Integer> temp = new Stack<>();
        backtrack(n,cols,master,slaver,temp,0,queens,res);
        return res;
    }

    private void backtrack(int n, Set<Integer> cols, Set<Integer> master, Set<Integer> slaver,
                           Stack<Integer> stack, int row,int[] queens,List<List<String>> res) {
        if (row ==n){
            List<String> board = convert2board(stack, n);
            res.add(board);
            return;
        }
        // 针对每一列，尝试是否可以放置
        for (int i =0;i<n;i++){
            if (!cols.contains(i)
            //row+col值相同说明在相同主对角线上
            && !master.contains(i+row)
            //row-col值相同，说明在同一副对角线上
            && !slaver.contains(i-row)
            ){
                //记录queen位置
                stack.push(queens[i]);
                cols.add(i);
                master.add(i+row);
                slaver.add(i-row);
                backtrack(n,cols,master,slaver,stack,row+1,queens,res);

                stack.pop();
                cols.remove(i);
                master.remove(i+row);
                slaver.remove(i-row);

            }
        }

    }
    private List<String> convert2board(Stack<Integer> stack, int n) {
        List<String> board = new LinkedList<>();
        for (Integer num : stack) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i==num){
                    sb.append("Q");
                }
                else {
                    sb.append(".");
                }
            }
//            sb.replace(num, num + 1, "Q");
            board.add(sb.toString());
        }
        return board;
    }

    /*------------------below is first approach, not--------------*/
    public List<List<String>> solveNQueens_1(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n<1) {
            return res;
        }
        String[][] board = new String[n][n];

        backtrack(0,n,board,res);

        return res;
    }

    private void backtrack(int row,int n,String[][] board,List<List<String>> res) {
        if (row == n){
            res.add(charToString(board));
            return;
        }
        for (int col =0;col<n;col++){
            if (! isValid(board,row,col,n)) {
                continue;
            }
            board[row][col] = "Q";
            backtrack(row+1,n,board,res);
            board[row][col] = ".";
        }
    }
    //实时计算，可能费时一点
    private boolean isValid(String[][] board, int row, int col,int n) {
        for (String[] line: board){
            if (line[col] == "Q"){
                return false;
            }
            //撇：row-1,and col+1
            for (int i = row -1,j = col +1;i>=0 && j<n; i--, j++){
                if (board[i][j] == "Q") return false;
            }
            //捺：row-1 and col-1
            for (int i = row -1,j = col -1;i>=0 && j>=0; i--, j--){
                if (board[i][j] == "Q") return false;
            }
        }
        return true;
    }

    private static List<String> charToString(String[][] array) {
        List<String> result = new ArrayList<>();
        for (String[] line : array) {
            StringBuilder builder = new StringBuilder();
            for (String s: line){
                if (s == null) {
                    s=".";
                }
                builder.append(s);
            }
            result.add(builder.toString());
        }
        return result;
    }
}
