package leetcodetop100;

public class LeetCode474 {


    /*
        给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
        请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
        如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] num = new int[len][2];
        for (int i = 0; i < strs.length; i++) {
            int total = strs[i].length();
            int j = 0;
            int countZero = 0;
            while (j < total) {
                if (strs[i].charAt(j) == '0') {
                    countZero++;
                }
                j++;
            }
            num[i][0] = countZero;
            num[i][1] = total - countZero;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < num.length; i++) {
            for (int j = m; j >= num[i][0]; j--) {
                for (int k = n; k >= num[i][1]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - num[i][0]][k - num[i][1]] + 1);
                }
            }
        }
        return dp[m][n];
    }


}
