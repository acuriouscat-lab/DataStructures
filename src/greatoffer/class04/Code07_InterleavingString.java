package greatoffer.class04;

// 本题测试链接 : https://leetcode-cn.com/problems/interleaving-string/
public class Code07_InterleavingString {
    // s3 是否是 s1 和 s2 的交错字符串
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str1.length + str2.length != str3.length) {
            return false;
        }
        int N = str1.length;
        int M = str2.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j <= str2.length; j++) {
            if (str2[j - 1] != str3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = (str1[i - 1] == str3[i + j - 1] &&
                        dp[i - 1][j]) || (str2[j - 1] == str3[i + j - 1] && dp[i][j - 1]);
            }
        }
        return dp[N][M];
    }

}
