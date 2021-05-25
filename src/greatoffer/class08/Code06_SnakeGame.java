package greatoffer.class08;

import java.util.Arrays;

public class Code06_SnakeGame {

    //给定一个矩阵matrix，值有正、负、0
    //蛇可以空降到最左列的任何一个位置，初始增长值是0
    //蛇每一步可以选择右上、右、右下三个方向的任何一个前进
    //沿途的数字累加起来，作为增长值；但是蛇一旦增长值为负数，就会死去
    //蛇有一种能力，可以使用一次：把某个格子里的数变成相反数
    //蛇可以走到任何格子的时候停止
    //返回蛇能获得的最大增长值
    public static int walk1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[] res = process1(matrix, i, j);
                ans = Math.max(ans, Math.max(res[0], res[1]));
            }
        }
        return ans;
    }

    // 来到(i,j)位置必须停止 返回用了一次和没用来到的最大增长值
    public static int[] process1(int[][] matrix, int i, int j) {
        if (j == 0) {
            return new int[]{matrix[i][j], -matrix[i][j]};
        }
        int[] preLeft = process1(matrix, i, j - 1);
        int preNo = preLeft[0];
        int preYes = preLeft[1];
        if (i > 0) {
            preLeft = process1(matrix, i - 1, j - 1);
            preNo = Math.max(preNo, preLeft[0]);
            preYes = Math.max(preYes, preLeft[1]);
        }
        if (i < matrix.length - 1) {
            preLeft = process1(matrix, i + 1, j - 1);
            preNo = Math.max(preNo, preLeft[0]);
            preYes = Math.max(preYes, preLeft[1]);
        }
        int no = -1;
        int yes = -1;
        if (preNo >= 0) {
            no = preNo + matrix[i][j];
            yes = preNo - matrix[i][j];
        }
        if (preYes >= 0) {
            yes = Math.max(yes, preYes + matrix[i][j]);
        }
        return new int[]{no, yes};
    }

    public static int walk2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[][][] dp = new int[matrix.length][matrix[0].length][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0][0] = matrix[i][0];
            dp[i][0][1] = -matrix[i][0];
            max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
        }
        for (int j = 1; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                int preUnuse = dp[i][j - 1][0];
                int preUse = dp[i][j - 1][1];
                if (i - 1 >= 0) {
                    preUnuse = Math.max(preUnuse, dp[i - 1][j - 1][0]);
                    preUse = Math.max(preUse, dp[i - 1][j - 1][1]);
                }
                if (i + 1 < matrix.length) {
                    preUnuse = Math.max(preUnuse, dp[i + 1][j - 1][0]);
                    preUse = Math.max(preUse, dp[i + 1][j - 1][1]);
                }
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
                if (preUnuse >= 0) {
                    dp[i][j][0] = matrix[i][j] + preUnuse;
                    dp[i][j][1] = -matrix[i][j] + preUnuse;
                }
                if (preUse >= 0) {
                    dp[i][j][1] = Math.max(dp[i][j][1], matrix[i][j] + preUse);
                }
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }
        return max;
    }

    public static int[][] generateRandomArray(int row, int col, int value) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 7;
        int M = 7;
        int V = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int r = (int) (Math.random() * (N + 1));
            int c = (int) (Math.random() * (M + 1));
            int[][] matrix = generateRandomArray(r, c, V);
            int ans1 = walk1(matrix);
            int ans2 = walk2(matrix);
            if (ans1 != ans2) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                break;
            }
        }
        System.out.println("finish");
    }


}
