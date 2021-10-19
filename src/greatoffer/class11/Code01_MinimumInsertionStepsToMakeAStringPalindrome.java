package greatoffer.class11;

import java.util.ArrayList;
import java.util.List;

public class Code01_MinimumInsertionStepsToMakeAStringPalindrome {

    //https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
    //问题一：一个字符串至少需要添加多少个字符能整体变成回文串
    //问题二：返回问题一的其中一种添加结果
    //问题三：返回问题一的所有添加结果

    // 1.
    public static int minInsertions(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int N = s.length();
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 0 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }

    //问题二：返回问题一的其中一种添加结果
    public static String minInsertionsOneWay(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        int L = 0;
        int R = N - 1;
        char[] ans = new char[N + dp[0][N - 1]];
        int ansL = 0;
        int ansR = ans.length - 1;
        while (L < R) {
            if (dp[L][R] == dp[L + 1][R] + 1) {
                ans[ansL++] = str[L];
                ans[ansR--] = str[L++];
            } else if (dp[L][R] == dp[L][R - 1] + 1) {
                ans[ansL++] = str[R];
                ans[ansR--] = str[R--];
            } else {
                ans[ansL++] = str[L++];
                ans[ansR--] = ans[R--];
            }
        }
        if (L == R) {
            ans[ansL] = str[L];
        }
        return String.valueOf(ans);
    }

    //问题三：返回问题一的所有添加结果
    public static List<String> minInsertionsAllWays(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 2) {
            res.add(s);
        } else {
            int N = s.length();
            int[][] dp = new int[N][N];
            for (int i = 0; i < N - 1; i++) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 0 : 1;
            }
            for (int i = N - 3; i >= 0; i--) {
                for (int j = i + 2; j < N; j++) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                    }
                }
            }
            int M = N + dp[0][N - 1];
            char[] path = new char[M];
            process(s, res, 0, N - 1, dp, path, 0, M - 1);

        }
        return res;
    }

    public static void process(String s, List<String> res, int L, int R, int[][] dp, char[] path, int pl, int pr) {
        if (L >= R) {
            if (L == R) {
                path[pl] = s.charAt(L);
            }
            res.add(String.valueOf(path));
        } else {
            if (dp[L][R] == dp[L + 1][R] + 1) {
                path[pl] = s.charAt(L);
                path[pr] = s.charAt(L);
                process(s, res, L + 1, R, dp, path, pl + 1, pr - 1);
            }
            if (dp[L][R] == dp[L][R - 1] + 1) {
                path[pl] = s.charAt(R);
                path[pr] = s.charAt(R);
                process(s, res, L, R - 1, dp, path, pl + 1, pr - 1);
            }
            if (s.charAt(L) == s.charAt(R) && (L == R - 1 || dp[L][R] == dp[L + 1][R - 1])) {
                path[pl] = s.charAt(L);
                path[pr] = s.charAt(R);
                process(s, res, L + 1, R - 1, dp, path, pl + 1, pr - 1);
            }
        }
    }

    public static void main(String[] args) {
        String s = null;
        String ans2 = null;
        List<String> ans3 = null;

        System.out.println("本题第二问，返回其中一种结果测试开始");
        s = "mbadm";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "leetcode";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "aabaa";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);
        System.out.println("本题第二问，返回其中一种结果测试结束");

        System.out.println();

        System.out.println("本题第三问，返回所有可能的结果测试开始");
        s = "mbadm";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "leetcode";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "aabaa";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();
        System.out.println("本题第三问，返回所有可能的结果测试结束");
    }


}
