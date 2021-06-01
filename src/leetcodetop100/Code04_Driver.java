package leetcodetop100;

import java.util.Arrays;

public class Code04_Driver {

    public static int maxMoney1(int[][] incomes) {
        if (incomes == null || incomes.length < 2) {
            return 0;
        }
        int N = incomes.length;
        int M = N >> 1;
        // dp[i][j] 表示 i..之后还未分配，A区域还有 j 辆需要分配的最大收益
        int[][] dp = new int[N + 1][M + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= M; j++) {
                if (j == 0) {
                    dp[i][j] = incomes[i][1] + dp[i + 1][j];
                } else if (j + i == N) {
                    dp[i][j] = incomes[i][0] + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(incomes[i][0] + dp[i + 1][j - 1], incomes[i][1] + dp[i + 1][j]);
                }
            }
        }
        return dp[0][M];
    }

    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    // 这题有贪心策略 :
    // 假设一共有10个司机，思路是先让所有司机去A，得到一个总收益
    // 然后看看哪5个司机改换门庭(去B)，可以获得最大的额外收益
    public static int maxMoney3(int[][] income) {
        int N = income.length;
        int[] arr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = income[i][1] - income[i][0];
            sum += income[i][0];
        }
        Arrays.sort(arr);
        int M = N >> 1;
        for (int i = N - 1; i >= M; i--) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            //int ans2 = maxMoney2(matrix);
            int ans3 = maxMoney3(matrix);
            if (ans1 != ans3) {
                System.out.println(ans1);
                // System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }


}
