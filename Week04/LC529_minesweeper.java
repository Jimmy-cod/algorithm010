package Week04;

public class LC529_minesweeper {
    static class Solution {
        int nr,nc;

        //1.点击的坐标click[0],click[1]
        //2.count周边8个方向的雷
        //3.count =0,代表没有相邻地雷的空方块（'E'）被挖出,bfs
        //4.else,board[x][y] 修改它为数字

        public char[][] updateBoard(char[][] board, int[] click) {
            //点击的坐标
            int x = click[0];
            int y = click[1];
            nr = board.length;
            nc = board[0].length;
            //一下就找到雷了 'M'
            if (board[x][y] == 'M'){
                board[x][y] = 'X';
                return board;
            }
            dfs(board,x,y);
            return board;
        }

        private void dfs(char[][] board, int x, int y) {
            if (x<0||x>=nr||y<0||y>=nc||board[x][y]!='E'){
                //出了边界或这方块已挖出！='E'
                return;
            }
            //找这方块周边有几个雷
            int mines = findMines(board,x,y);
            //mines == 0代表没有相邻地雷的空方块（'E'）被挖出，
            //根据要求：修改它为（'B'），并且所有和其相邻的方块都应该被递归地揭露。
            if (mines == 0){
                //'B'表示没有雷，要往8个方向自动探索
                board[x][y] = 'B';
                for (int i=-1;i<2;i++) {
                    for (int j = -1; j < 2; j++) {
                        dfs(board,i+x,j+y);
                    }
                }
            }
            //修改它为数字（'1'到'8'），表示相邻地雷的数量
            else{
                board[x][y]=(char) ('0'+mines);
            }
        }

        private int findMines(char[][] board, int x, int y) {
            //周边有8个方块
            int count=0;
            for (int i=-1;i<2;i++){
                for (int j=-1;j<2;j++){
                    //x1,y1代表周边8个方块的坐标
                    int x1= i+x;
                    int y1= j+y;
                    //出了边界
                    if (x1<0||x1>=nr||y1<0||y1>=nc){
                        continue;
                    }
                    if (board[x1][y1] == 'M'){
                        count++;
                    }
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int mines =8;
        char c = (char) ('0'+ mines);
        System.out.println(c);
    }
}
