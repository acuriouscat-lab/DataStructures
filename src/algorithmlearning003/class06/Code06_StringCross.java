package algorithmlearning003.class06;

public class Code06_StringCross {

    /*

    给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符，
    而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的字符之间保持 原来在str2中的顺序，那么称aim是str1和str2的交错组成。
    实现一个函数，判断aim是 否是str1和str2交错组成
    【举例】 str1="AB"，str2="12"。那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"等都是 str1 和 str2 的 交错组成
     */
    public static boolean isCross1(String str1, String str2, String ai) {

        if (ai == null || str1 == null || str2 == null) {
            return false;
        }

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        char[] arr = ai.toCharArray();

        int N = s1.length;
        int M = s2.length;
        int aLen = arr.length;

        if (aLen != N + M) {
            return false;
        }

        boolean[][] dp = new boolean[N + 1][M + 1];
//       dp[N][M] = true;
//       for (int i = M - 1; i >= 0; i--) {
//           dp[N][i] = dp[N][i + 1] && arr[i] == s2[i];
//       }
//
//       for (int i = N - 1; i >= 0; i--) {
//           dp[i][M] = dp[i + 1][M] && arr[i] == s1[i];
//       }
//
//       for (int i = N - 1; i >= 0; i--) {
//           for (int j = M - 1; j >= 0; j--) {
//               if ((s1[i] == arr[i + j] && dp[i + 1][j]) || (s2[j] == arr[i + j] && dp[i][j + 1])) {
//                   dp[i][j] = true;
//               }
//           }
//       }
//
//       return dp[0][0];

        dp[0][0] = true;
        for (int i = 1; i <= M; i++) {
            if (s2[i - 1] != arr[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= N; i++) {
            if (s1[i - 1] != arr[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if ((s1[i - 1] == arr[i - 1 + j] && dp[i - 1][j]) || (s2[j - 1] == arr[i + j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[N][M];

    }

    public static boolean isCross2(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) {
            return false;
        }
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        char[] chaim = aim.toCharArray();
        if (chaim.length != ch1.length + ch2.length) {
            return false;
        }
        char[] longs = ch1.length >= ch2.length ? ch1 : ch2;
        char[] shorts = ch1.length < ch2.length ? ch1 : ch2;
        boolean[] dp = new boolean[shorts.length + 1];
        dp[0] = true;
        for (int i = 1; i <= shorts.length; i++) {
            if (shorts[i - 1] != chaim[i - 1]) {
                break;
            }
            dp[i] = true;
        }
        for (int i = 1; i <= longs.length; i++) {
            dp[0] = dp[0] && longs[i - 1] == chaim[i - 1];
            for (int j = 1; j <= shorts.length; j++) {
                if ((longs[i - 1] == chaim[i + j - 1] && dp[j]) || (shorts[j - 1] == chaim[i + j - 1] && dp[j - 1])) {
                    dp[j] = true;
                } else {
                    dp[j] = false;
                }
            }
        }
        return dp[shorts.length];
    }

    public static void main(String[] args) {
        String str1 = "1234";
        String str2 = "abcd";
        String aim = "1a23bcd4";
        System.out.println(isCross1(str1, str2, aim));
        System.out.println(isCross2(str1, str2, aim));

    }


}
