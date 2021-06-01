package leetcodetop100;

public class Problem_0348_DesignTicTacToe {

    //设计井字棋
    class TicTacToe{
        private int[][] row;
        private int[][] col;
        private int[] leftUp;
        private int[] rightUp;
        private boolean[][] matrix;
        private int N;
        public TicTacToe(int n){
            // row[i][1] -- > 1 玩家在 i 行下了几个棋子
            row = new int[n][3];
            col = new int[n][3];
            leftUp = new int[3];
            rightUp = new int[3];
            matrix = new boolean[n][n];
            N = n;
        }

        /**
         *
         * @param r 下在了 row 行
         * @param c 下在了 col 列
         * @param player 哪个玩家
         * @return 是否赢了
         */
        public int move(int r,int c,int player){
            matrix[r][c] = true;
            if(++row[r][player] == N){
                return player;
            }
            if(++col[c][player] == N){
                return player;
            }
            if(r == c && ++leftUp[player] == N){
                return player;
            }
            if(r + c == N - 1 && ++rightUp[player] == N){
                return player;
            }
            return 0;
        }
    }



}
