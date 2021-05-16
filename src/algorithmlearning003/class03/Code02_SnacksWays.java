package algorithmlearning003.class03;

public class Code02_SnacksWays {

    //背包容量为w
    //一共有n袋零食, 第i袋零食体积为v[i]
    //总体积不超过背包容量的情况下，
    //一共有多少种零食放法？(总体积为0也算一种放法)。
    public static int ways1(int[] arr, int w) {
        // arr[0...]
        return process(arr, 0, w);
    }

    private static int process(int[] arr, int index, int rest) {
        if(rest < 0){
            return 0;
        }
        if(index == arr.length){
            return 1;
        }

        int no = process(arr,index + 1,rest);
        int yes = process(arr,index + 1,rest - arr[index]);
        return no + yes;
    }

    public static int ways2(int[] arr,int w){
        int N = arr.length;
        int[][] dp = new int[N + 1][w + 1];
        for(int i = 0; i < w + 1; i++){
            dp[N][i] = 1;
        }
        for(int i = N - 1; i >= 0; i--){
            for(int j = 0; j < w + 1; j++){
                dp[i][j] = dp[i + 1][j] + (j - arr[i] < 0 ? 0 : dp[i+ 1][j - arr[i]]);
            }
        }
        return dp[0][w];
    }
    public static int ways3(int[] arr, int w) {
        int N = arr.length;
        int[][] dp = new int[N][w + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= w) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= w; j++) {
                dp[i][j] = dp[i - 1][j] + ((j - arr[i]) >= 0 ? dp[i - 1][j - arr[i]] : 0);
            }
        }
        int ans = 0;
        for (int j = 0; j <= w; j++) {
            ans += dp[N - 1][j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 2, 9 };
        int w = 8;
        System.out.println(ways1(arr, w));
        System.out.println(ways2(arr, w));
        System.out.println(ways3(arr, w));

    }

}
