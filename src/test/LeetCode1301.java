package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode1301 {
    public static void main(String[] args) {
        List<String> board = new ArrayList<>();
        //["E23","2X2","12S"]
        // ["E11","XXX","11S"]
//        boadr.add("E23");
//        boadr.add("2X2");
//        boadr.add("12S");
        board.add("E11");
        board.add("XXX");
        board.add("12S");
        pathsWithMaxScore(board);
    }

    public  static int m;
    public static int n;
    static int mod = (int)1e9 + 7;

    public static int[] pathsWithMaxScore(List<String> board) {
        int N = board.size();
        // dp[i][j]当前来到 i j 位置最大的路径和
        int[][] dp = new int[N][N];
        int[][] ways = new int[N][N];
        int[] res = new int[2];
        if (board.get(0).charAt(0) != 'E' || board.get(N - 1).charAt(N - 1) != 'S') {
            return res;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (i == N - 1 && j == N - 1) {
                    ways[i][j] = 1;
                    continue;
                }
                if (board.get(i).charAt(j) == 'X') {
                    dp[i][j] = Integer.MIN_VALUE;
                    continue;
                }
                int val = (i == 0 && j == 0) ? 0 : board.get(i).charAt(j) - '0';
                int score = Integer.MIN_VALUE;
                int way = 0;
                if (i + 1 < N) {
                    int curScore = dp[i + 1][j] + val;
                    int curWay = ways[i + 1][j];
                    int[] tmp = update(curScore, curWay, score, way);
                    score = tmp[0];
                    way = tmp[1];
                }
                if (j + 1 < N) {
                    int curScore = dp[i][j + 1] + val;
                    int curWay = ways[i][j + 1];
                    int[] tmp = update(curScore, curWay, score, way);
                    score = tmp[0];
                    way = tmp[1];
                }
                if (i + 1 < N && j + 1 < N) {
                    int curScore = dp[i + 1][j + 1] + val;
                    int curWay = ways[i + 1][j + 1];
                    int[] tmp = update(curScore, curWay, score, way);
                    score = tmp[0];
                    way = tmp[1];
                }
                dp[i][j] = score < 0 ? Integer.MIN_VALUE : score;
                ways[i][j] = way;
            }
        }
        res[0] = dp[0][0] == Integer.MIN_VALUE ? 0 : dp[0][0];
        res[1] = dp[0][0] == Integer.MIN_VALUE ? 0 : ways[0][0];
        return res;
    }


    public static  int[] update(int curScore,int curWay,int score,int way){
        int[] res = new int[]{score, way};
        if (curScore > score) {
            res[0] = curScore;
            res[1] = curWay;
        } else if (curScore == score && curScore != Integer.MIN_VALUE) {
            res[1] += curWay;
        }
        res[1] %= mod;
        return res;
    }

    public static int[] parseIndex(int index){
        return new int[]{index / n, index % n};
    }


    public static int getIndex(int x, int y) {
        return x * n + y;
    }


}
