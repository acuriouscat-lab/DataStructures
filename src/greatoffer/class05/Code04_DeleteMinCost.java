package greatoffer.class05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Code04_DeleteMinCost {

    // 题目：
    // 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
    // 比如 s1 = "abcde"，s2 = "axbc"
    // 返回 1

    // 解法一
    // 求出str2所有的子序列，然后按照长度排序，长度大的排在前面。
    // 然后考察哪个子序列字符串和s1的某个子串相等(KMP)，答案就出来了。
    // 分析：
    // 因为题目原本的样本数据中，有特别说明s2的长度很小。所以这么做也没有太大问题，也几乎不会超时。
    // 但是如果某一次考试给定的s2长度远大于s1，这么做就不合适了。
    public static int minCost1(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return -1;
        }
        ArrayList<String> all = new ArrayList<>();
        process(s2.toCharArray(), 0, "", all);
        all.sort(((o1, o2) -> o2.length() - o1.length()));
        for (String str : all) {
            if (s1.contains(str)) {
                return s2.length() - str.length();
            }
        }
        return s2.length();
    }

    public static void process(char[] str, int index, String path,ArrayList<String> all) {
        if (index == str.length) {
            all.add(path);
            return;
        }
        process(str, index + 1, path, all);
        process(str, index + 1, path + str[index], all);
    }


    public static int minCost2(String s1,String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        int N = s1.length();
        int ans = Integer.MAX_VALUE;
        char[] str2 = s2.toCharArray();
        for (int start = 0; start < N ; start++) {
            for (int end = start + 1; end <= N; end++) {
                ans = Math.min(ans, distance(str2, s1.substring(start, end).toCharArray()));
            }
        }
        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }


    public static int distance(char[] str2, char[] s1sub) {
        int row = str2.length;
        int col = s1sub.length;
        int[][] dp = new int[row][col];
        // dp[i][j]的含义：
        // str2[0..i]仅通过删除行为变成s1sub[0..j]的最小代价
        // 可能性一：
        // str2[0..i]变的过程中，不保留最后一个字符(str2[i])，
        // 那么就是通过str2[0..i-1]变成s1sub[0..j]之后，再最后删掉str2[i]即可 -> dp[i][j] = dp[i-1][j] + 1
        // 可能性二：
        // str2[0..i]变的过程中，想保留最后一个字符(str2[i])，然后变成s1sub[0..j]，
        // 这要求str2[i] == s1sub[j]才有这种可能, 然后str2[0..i-1]变成s1sub[0..j-1]即可
        // 也就是str2[i] == s1sub[j] 的条件下，dp[i][j] = dp[i-1][j-1]
        dp[0][0] = str2[0] == s1sub[0] ? 0 : Integer.MAX_VALUE;
        for (int j = 1; j < col; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == s1sub[0]) ? i : Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (str2[i] == s1sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }

            }
        }
        return dp[row - 1][col - 1];
    }



    // 时间复杂度O(N * M平方)
    // 复杂度和方法三一样，但是思路截然不同
    public static int minCost4(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        HashMap<Character, ArrayList<Integer>> map1 = new HashMap<>();
        // 将 s1 中，字符出现的位置放入到 map 表中
        for (int i = 0; i < str1.length; i++) {
            ArrayList<Integer> list = map1.getOrDefault(str1[i], new ArrayList<Integer>());
            list.add(i);
            map1.put(str1[i], list);
        }
        int ans = 0;
        // 假设删除后的str2必以i位置开头
        // 那么查找i位置在str1上一共有几个，并对str1上的每个位置开始遍历
        // 再次遍历str2一次，看存在对应str1中i后续连续子串可容纳的最长长度
        for (int i = 0; i < str2.length; i++) {// 枚举 s2 中任意一个字符为开头的情况
            if (map1.containsKey(str2[i])) {
                ArrayList<Integer> keyList = map1.get(str2[i]);
                for (int j = 0; j < keyList.size(); j++) {
                    int cur1 = keyList.get(j) + 1;
                    int cur2 = i + 1;
                    int count = 1;
                    for (int k = cur2; k < str2.length && cur1 < str1.length; k++) {
                        if (str2[k] == str1[cur1]) {
                            cur1++;
                            count++;
                        }
                    }
                    ans = Math.max(ans, count);
                }
            }
        }
        return s2.length() - ans;
    }

    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {

        char[] x = { 'a', 'b', 'c', 'd' };
        char[] y = { 'a', 'd' };

       // System.out.println(onlyDelete(x, y));

        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 10000;
        boolean pass = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = minCost1(str1, str2);
            int ans2 = minCost2(str1, str2);
           // int ans3 = minCost3(str1, str2);
        //    int ans4 = minCost4(str1, str2);
            if (ans1 != ans2 ) {
                pass = false;
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
//                System.out.println(ans3);
//                System.out.println(ans4);
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }


}
