package greatoffer.class18;

import java.util.Scanner;

/**
 * @author Administrator
 * @Description https://www.nowcoder.com/questionTerminal/8ecfe02124674e908b2aae65aad4efdf
 * @create 2021-07-19 16:47
 */
public class Code03_CherryPickup {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] grid = new int[N][M];
        for(int i = 0; i < N; i++){
            for (int j = 0; j < M; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println(cherryPickUp(grid));
        sc.close();
    }

    public static int cherryPickUp(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[][][] dp = new int[N][M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        int ans = process(grid, 0, 0, 0, dp);
        return ans < 0 ? 0 : ans;
    }

    // 从 grid[x1][y1] 和 grid[x2][y2] 出发后续能够得到的最好的分数
    public static int process(int[][] grid, int x1, int y1, int x2, int[][][] dp) {
        if (x1 == grid.length || x2 == grid.length || y1 == grid[0].length || x1 + y1 - x2 == grid[0].length) {
            return Integer.MIN_VALUE;
        }
        if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
            return dp[x1][y1][x2];
        }
        if (x1 == grid.length - 1 && y1 == grid[0].length - 1) {
            dp[x1][y1][x2] = grid[x1][y1];
            return dp[x1][y1][x2];
        }
        int next = Integer.MIN_VALUE;
        next = Math.max(next, process(grid, x1 + 1, y1, x2 + 1, dp));
        next = Math.max(next, process(grid, x1 + 1, y1, x2, dp));
        next = Math.max(next, process(grid, x1, y1 + 1, x2 + 1, dp));
        next = Math.max(next, process(grid, x1, y1 + 1, x2 , dp));
        if (x1 == x2) {
            dp[x1][y1][x2] = grid[x1][y1] +  next;
            return dp[x1][y1][x2];
        }
        dp[x1][y1][x2] = grid[x1][y1] + grid[x2][x1 + y1 - x2] + next;
        return dp[x1][y1][x2];
    }






}
