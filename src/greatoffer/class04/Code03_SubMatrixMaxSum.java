package greatoffer.class04;

public class Code03_SubMatrixMaxSum {

    // 最大子矩阵和
    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        // O(N^2 * M)
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        // 将子矩阵问题转化为 一维的 子数组最大累加和问题
        for (int i = 0; i < N; i++) {
            // i~j
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    s[k] += m[j][k];
                }
                max = Math.max(max, maxSubArray(s));
            }
        }
        return max;
    }

    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = Math.max(cur, 0);
        }
        return max;
    }

    public static int[] getMaxMatrix(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int b = 0;//开始的列
        int a = 0;//开始的行
        int c = 0;//结束的列
        int d = 0;//结束的行
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                int begin = 0;
                for (int k = 0; k < M; k++) {
                    s[k] += matrix[j][k];
                    cur += s[k];
                    if (cur > max) {
                        max = cur;
                        a = i;
                        b = begin;
                        c = j;
                        d = k;
                    }
                    if(cur < 0){
                        cur = 0;
                        begin = k + 1;
                    }
                }
            }
        }
        return new int[]{a, b, c, d};
    }



}
