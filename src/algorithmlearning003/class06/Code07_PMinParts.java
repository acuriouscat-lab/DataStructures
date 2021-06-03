package algorithmlearning003.class06;

public class Code07_PMinParts {


    // 一个字符串 至少切几刀可以 分出 都是回文字串
    public static int minParts(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        if(s.length() == 1)
            return 1;
        char[] str = s.toCharArray();
        int l = str.length;
        // 范围上的尝试模型 l ... r
        boolean[][] dp = new boolean[l][l];
        for (int i = 0; i < l; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < l - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1];
        }
        for (int i = l - 3; i >= 0; i--) {
            for (int j = i + 2; j < l; j++) {
                dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1];
            }
        }
        // 从左往右的尝试模型
        // dp[i] 表示 i.... 需要至少切割几刀能够划分出的回文串长度为多少
        int[] dp2 = new int[l + 1];
        for (int i = 0; i < l; i++) {
            dp2[i] = Integer.MAX_VALUE;
        }
        dp2[l] = 0;
        for (int i = l - 1; i >= 0; i--) {
            for (int end = i; end < l; end++) {
                if(dp[i][end]){
                    dp2[i] = Math.min(dp2[i], 1 + dp2[end + 1]);
                }
            }
        }

        return dp2[0];
    }

    public static void main(String[] args) {
        String test = "aba12321412321TabaKFK";
        System.out.println(minParts(test));
    }






}
