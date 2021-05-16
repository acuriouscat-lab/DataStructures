package algorithmlearning.class12;

public class Code01_RobotWalk {

    /*
        假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
        开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
        如果机器人来到1位置，那么下一步只能往右来到2位置；
        如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
        如果机器人来到中间位置，那么下一步可以往左走或者往右走；
        规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
        给定四个参数 N、M、K、P，返回方法数。
     */
    public static int ways1(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        // 总共N个位置，从M点出发，还剩K步，返回最终能达到P的方法数
        return walk(N, M, K, P);
    }
    // N : 位置为1 ~ N，固定参数
    // cur : 当前在cur位置，可变参数
    // rest : 还剩res步没有走，可变参数
    // P : 最终目标位置是P，固定参数
    // 该函数的含义：只能在1~N这些位置上移动，当前在cur位置，走完rest步之后，停在P位置的方法数作为返回值返回
    private static int walk(int N, int cur, int rest, int P) {
        // 如果没有剩余步数了，当前的cur位置就是最后的位置
        // 如果最后的位置停在P上，那么之前做的移动是有效的
        // 如果最后的位置没在P上，那么之前做的移动是无效的
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        // 如果还有rest步要走，而当前的cur位置在1位置上，那么当前这步只能从1走向2
        // 后续的过程就是，来到2位置上，还剩rest-1步要走
        if (cur == 1) {
            return walk(N, 2, rest - 1, P);
        }
        // 如果还有rest步要走，而当前的cur位置在N位置上，那么当前这步只能从N走向N-1
        // 后续的过程就是，来到N-1位置上，还剩rest-1步要走
        if (cur == N) {
            return walk(N, cur - 1, rest - 1, P);
        }
        // 如果还有rest步要走，而当前的cur位置在中间位置上，那么当前这步可以走向左，也可以走向右
        // 走向左之后，后续的过程就是，来到cur-1位置上，还剩rest-1步要走
        // 走向右之后，后续的过程就是，来到cur+1位置上，还剩rest-1步要走
        // 走向左、走向右是截然不同的方法，所以总方法数要都算上
        return walk(N, cur - 1, rest - 1, P) + walk(N, cur + 1, rest - 1, P);
    }

    //因为暴力递归的过程中，存在重复递归求解
    //所以选择记忆化搜索进行优化   加缓存
    public static int waysCache(int N, int M, int K, int P) {
        if (N < 2 || M > N || M < 1 || P < 1 || K < 1 || P > N) {
            return 0;
        }

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return walkCache(N, M, K, P, dp);
    }

    private static int walkCache(int N, int cur, int rest, int P, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }

        if (rest == 0) {
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        // 如果还有rest步要走，而当前的cur位置在1位置上，那么当前这步只能从1走向2
        // 后续的过程就是，来到2位置上，还剩rest-1步要走
        if (cur == 1) {
            dp[cur][rest] = walkCache(N, 2, rest - 1, P, dp);
            return dp[cur][rest];
        }
        // 如果还有rest步要走，而当前的cur位置在N位置上，那么当前这步只能从N走向N-1
        // 后续的过程就是，来到N-1位置上，还剩rest-1步要走
        if (cur == N) {
            dp[cur][rest] = walkCache(N, cur - 1, rest - 1, P,dp);
            return  dp[cur][rest];
        }
        // 如果还有rest步要走，而当前的cur位置在中间位置上，那么当前这步可以走向左，也可以走向右
        // 走向左之后，后续的过程就是，来到cur-1位置上，还剩rest-1步要走
        // 走向右之后，后续的过程就是，来到cur+1位置上，还剩rest-1步要走
        // 走向左、走向右是截然不同的方法，所以总方法数要都算上
        dp[cur][rest] = walkCache(N, cur - 1, rest - 1, P, dp) + walkCache(N, cur + 1, rest - 1, P, dp);
        return dp[cur][rest];
    }

    public static int ways2(int N, int M, int K, int P) {
        if (N < 2 || M > N || M < 1 || P < 1 || K < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];
        dp[0][P] = 1;
        //填表
        for (int i = 1; i <= K; i++) {//还有多少步
            for (int j = 1; j <= N; j++) {//当前走到哪里
                if (j == 1) {
                    dp[i][j] = dp[i-1][2];
                } else if (j == N) {
                    dp[i][j] = dp[i-1][N - 1];
                }else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][M];
    }
    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));
        System.out.println(waysCache(7, 4, 9, 5));
//        System.out.println(ways3(7, 4, 9, 5));
//        System.out.println(ways4(7, 4, 9, 5));
//        System.out.println(ways5(7, 4, 9, 5));
    }
}
