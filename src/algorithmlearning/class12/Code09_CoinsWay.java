package algorithmlearning.class12;

public class Code09_CoinsWay {


    /*
        给定数组arr，arr中所有的值都为正数且不重复
        每个值代表一种面值的货币，每种面值的货币可以使用任意张
        再给定一个整数 aim，代表要找的钱数
        求组成 aim 的方法数
     */
    public static int ways1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        return process1(arr, 0, aim);
    }

    //可以自由使用index..之后所有的值 每一张都可以使用任意张
    //返回有多少种
    private static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
            ways += process1(arr,index+1,rest-arr[index]*zhang);
        }
        return ways;
    }

    public static int ways2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(arr, 0, aim, dp);
    }
    // 如果index和rest的参数组合，是没算过的，dp[index][rest] == -1
    // 如果index和rest的参数组合，是算过的，dp[index][rest]  > - 1
    private static int process2(int[] arr, int index, int rest, int[][] dp) {
        if(dp[index][rest] != -1){
            return dp[index][rest];
        }
        if(index == arr.length) {
            dp[index][rest] = rest == 0 ? 1 :0;
            return  dp[index][rest];
        }
        int ways = 0;
        for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
            ways += process2(arr, index + 1, rest - (arr[index] * zhang), dp);
        }
        dp[index][rest] = ways;
        return ways;
    }

    //经典动态规划
    public static int ways3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] =1 ;
        for (int index = N - 1; index >= 0; index--) {//但是每个格子存在枚举行为，还可以进行优化
            for (int rest = 0; rest < aim + 1; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int ways4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {//第几张
            for (int rest = 0; rest < aim + 1; rest++) {//剩下多少
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = { 5, 10,50,100 };
        int sum = 1000;
        System.out.println(ways1(arr, sum));
        System.out.println(ways2(arr, sum));
        System.out.println(ways3(arr, sum));
        System.out.println(ways4(arr, sum));
    }
}
