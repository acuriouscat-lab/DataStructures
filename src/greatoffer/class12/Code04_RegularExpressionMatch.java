package greatoffer.class12;

// https://leetcode-cn.com/problems/regular-expression-matching/
public class Code04_RegularExpressionMatch {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int N = s.length();
        int M = p.length();
        // dp[i][j] 表示 s 的 i 长度和 p 的 j 长度是否能否匹配
        boolean[][] dp = new boolean[N + 1][M + 1];
        // 初始值
        dp[0][0] = true;
        // 能否匹配 p 为空 =》 让 * 匹配空
        for (int i = 2; i <= M; i++) {
            dp[0][i] = (p.charAt(i - 1) == '*') && dp[0][i - 2];
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (p.charAt(j - 1) == '*') {
                    // * 和之前的字符匹配空
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[N][M];
    }

    // s[i - 1] 能否匹配 p[j - 1]
    public boolean matches(String s, String p, int i, int j) {
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
