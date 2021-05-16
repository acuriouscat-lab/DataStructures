package algorithmlearning003.class04;

public class Code07_SubMatrixMaxSum {
    //给定一个整型矩阵，返回子矩阵的最大累计和。
    //根据求子数组的最大累加和的思路
    //依次求 0.0 0..1 ... 0..n-1
    // 1.1 1..2, 1...n
    //每次累加当前到来的行到s对应的列
    public static int maxSum(int[][] m) {

        if (m == null || m.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int cur;
        int[] s;

        for (int i = 0; i < m.length; i++) {
            s = new int[m[0].length];
            for (int j = i; j < m.length; j++) {
                cur = 0;
                for (int k = 0; k < s.length; k++) {
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
        System.out.println(maxSum(matrix));
        int[][] matrix2 = { { -1, 1, 1 }, { 2, -2, 2 }, { -1, -1, 1 } };
        System.out.println(maxSum(matrix2));

    }



}
