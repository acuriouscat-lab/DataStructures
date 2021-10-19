package greatoffer.class09;

public class Code03_RegularExpressionMatch {

    // 测试链接 : https://leetcode.com/problems/regular-expression-matching/

    public static boolean isValid(char[] s, char[] e) {
        // s中不能有'.' or '*'
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        // 开头的e[0]不能是'*'，没有相邻的'*'
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    // 初始尝试版本，不包含斜率优化
    public static boolean isMatch1(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) && process(s, e, 0, 0);
    }

    public static boolean process(char[] s, char[] e, int si, int ei) {
        if (ei == e.length) {
            return si == s.length;
        }
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            return si != s.length && (s[si] == e[ei] || e[ei] == '.') && process(s, e, si + 1, ei + 1);
        }
        while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
            if (process(s, e, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process(s, e, si, ei + 2);
    }

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        int N = s.length;
        int M = e.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int i = M - 1; i >= 0; i--) {
            dp[N][i] = (i + 1 < M && e[i + 1] == '*') && dp[N][i + 2];
        }
        if (N > 0 && M > 0) {
            dp[N - 1][M - 1] = (s[N - 1] == e[M - 1] || e[M - 1] == '.');
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
                } else {
                    if ((s[i] == e[j] || e[j] == '.') && dp[i + 1][j]) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }


    public boolean isMatch2(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        int N = s.length();
        int M = p.length();
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][0] = true;
        for(int i = 2; i <= M; i++){
            dp[0][i] = (p.charAt(i - 1) == '*') && dp[0][i - 2];
        }
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i][j - 2];
                    if(matches(s,p,i,j - 1)){
                        dp[i][j] |= dp[i - 1][j];
                    }
                }else{
                    if(matches(s,p,i,j)){
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[N][M];
    }

    public boolean matches(String s, String p,int i,int j){
        if(p.charAt(j - 1) == '.'){
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }


}
