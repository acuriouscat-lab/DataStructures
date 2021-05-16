package algorithmlearning.class12;

public class Code06_Coffee {
    /*
        给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
        只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
        每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
        返回让所有咖啡杯变干净的最早完成时间
        三个参数：int[] arr、int a、int b
     */
    public static int process(int[] drinks, int a, int b, int index, int washLine) {
        if (index == drinks.length - 1) {
            return Math.min(Math.max(washLine, drinks[index] + b), Math.max(washLine, drinks[index]) + a);
        }

        int wash = Math.max(washLine, drinks[index]) + a;
        int next1 = process(drinks, a, b, index + 1, wash);
        int p1 = Math.max(wash, next1);

        int dry = drinks[index] + a;
        int next2 = process(drinks, a, b, index, washLine);
        int p2 = Math.max(dry, next2);

        return Math.min(p1, p2);

    }

    public static int dp(int[] drinks, int a, int b) {
        if (a >= b) {
            return drinks[drinks.length - 1] + b;
        }

        int N = drinks.length;
        int limit = 0 ;
        for (int i = 0; i < N; i++) {
            limit = Math.max(limit, drinks[i] + a);
        }

        int[][] dp = new int[N][limit + 1];

        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[N - 1][washLine] = Math.min(Math.max(washLine, drinks[N - 1]) + a, drinks[N - 1] + b);
        }

        for (int index = N - 2; index >= 0; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(washLine, drinks[index]) + a;
                if (wash <= limit) {
                    p1 = Math.max(wash, dp[index + 1][washLine]);
                }
                int p2 = Math.max(drinks[index] + b, dp[index + 1][washLine]);
                dp[index][washLine] = Math.min(p1, p2);
            }
        }

        return dp[0][0];
    }

}
