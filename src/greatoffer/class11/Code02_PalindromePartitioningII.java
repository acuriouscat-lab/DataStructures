package greatoffer.class11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 本题测试链接 : https://leetcode-cn.com/problems/palindrome-partitioning-ii/
public class Code02_PalindromePartitioningII {

    //问题一：一个字符串至少要切几刀能让切出来的子串都是回文串
    public int minCut(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] dp = createCheckMap(str, N);
        // min[i] 表示 i.... 之后能分成几部分都是回文串
        int[] min = new int[N + 1];
        Arrays.fill(min, N);
        min[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (dp[i][N - 1]) {
                min[i] = 1;
            } else {
                for (int end = i; end < N; end++) {
                    if (dp[i][end]) {
                        min[i] = Math.min(min[i], min[end + 1] + 1);
                    }
                }
            }
        }
        return min[0] - 1;
    }

    public static boolean[][] createCheckMap(char[] str, int N) {
        boolean[][] dp = new boolean[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = true;
            dp[i][i + 1] = str[i] == str[i + 1];
        }
        dp[N - 1][N - 1] = true;
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1];
            }
        }
        return dp;
    }


    //问题二：返回问题一的其中一种划分结果
    public static List<String> minCutOneWay(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 2) {
            res.add(s);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = createCheckMap(str, N);
            int[] dp = new int[N];
            Arrays.fill(dp, N);
            dp[N] = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                        }
                    }
                }
            }
            for (int i = 0, j = 1; j <= N; j++) {
                if (checkMap[i][j - 1] && dp[i] == dp[j + 1]) {
                    res.add(s.substring(i, j));
                    i = j;
                }
            }
        }
        return res;
    }


    //问题三：返回问题一的所有划分结果
    public static List<List<String>> minCutAllWays(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() < 2) {
            List<String> cur = new ArrayList<>();
            cur.add(s);
            res.add(cur);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = createCheckMap(str, N);
            int[] dp = new int[N + 1];
            dp[N] = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = 1 + next;
                }
            }
            process(s, 0, 1, checkMap, dp, new ArrayList<>(), res);
        }
        return res;
    }

    public static void process(String s, int i, int j, boolean[][] checkMap, int[] dp,
                               List<String> path, List<List<String>> res) {
        if (j == s.length()) {
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
        }else{
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                process(s, j, j + 1, checkMap, dp, path, res);
                path.remove(path.size() - 1);
            }
            process(s, i, j + 1, checkMap, dp, path, res);
        }
    }
}
