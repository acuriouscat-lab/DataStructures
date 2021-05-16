package greatoffer.class01;

public class Code05_LongestIncreasingPath {

    //给定一个二维数组matrix，
    //你可以从任何位置出发，走向上下左右四个方向
    //返回能走出来的最长的递增链长度

    public static int longestIncreasingPath1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int ans = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    public static int process1(int[][] matrix, int i, int j) {
        int up = (i > 0 && matrix[i - 1][j] > matrix[i][j]) ? process1(matrix, i - 1, j) : 0;
        int down = (i < matrix.length - 1 && matrix[i + 1][j] > matrix[i][j]) ? process1(matrix, i + 1, j) : 0;
        int left = (j > 0 && matrix[i][j - 1] > matrix[i][j]) ? process1(matrix, i, j - 1) : 0;
        int right = (i < matrix[0].length - 1 && matrix[i][j + 1] > matrix[i][j]) ? process1(matrix, i, j + 1) : 0;
        return Math.max(Math.max(up,down),Math.max(left,right)) + 1;
    }

    // 记忆化搜索
    public static int longestIncreasingPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int ans = 1;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, process2(matrix, i, j,dp));
            }
        }
        return ans;
    }

    public static int process2(int[][] matrix, int i, int j,int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int up = (i > 0 && matrix[i - 1][j] > matrix[i][j]) ? process2(matrix, i - 1, j,dp) : 0;
        int down = (i < matrix.length - 1 && matrix[i + 1][j] > matrix[i][j]) ? process2(matrix, i + 1, j,dp) : 0;
        int left = (j > 0 && matrix[i][j - 1] > matrix[i][j]) ? process2(matrix, i, j - 1,dp) : 0;
        int right = (i < matrix[0].length - 1 && matrix[i][j + 1] > matrix[i][j]) ? process2(matrix, i, j + 1, dp) : 0;
        dp[i][j] = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        return dp[i][j];
    }


}
