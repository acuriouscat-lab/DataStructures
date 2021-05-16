package algorithmlearning.class12;

public class Code05_PalindromSubsequence {

    //两个字符串的最长公共子序列问题
    //一个样本做行一个样本做列的样本对应模型
    public static int longestCommonSequence(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str2.length; i++) {//第一行
            dp[0][i] = Math.max(dp[0][i-1], str1[0] == str2[i] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {//第一列
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        //其他任意位置
        //dp[i][j] 以i和j结尾的最长公共子序列的最大长度是多少
        /*
            1)既不是以i结尾，又不是以j结尾  ==》 dp[i-1][j-1]
            2)以i结尾，不以j结尾          ==》 dp[i][j-1]
            3)以j结尾，不以i结尾          ==》 dp[i-1][j]
            4)以i和j结尾                 ==》 dp[i-1][j-1] + 1
                    (i-1,j-1)  (i-1,j)
                    (i,j-1)    (i,j)
         */
        for (int str1Index = 1; str1Index < str1.length; str1Index++) {
            for (int str2Index = 1; str2Index < str2.length; str2Index++) {
                dp[str1Index][str2Index] = Math.max(dp[str1Index - 1][str2Index], dp[str1Index][str2Index - 1]);
                if (str1[str1Index] == str2[str2Index]) {
                    dp[str1Index][str2Index] = Math.max(dp[str1Index][str2Index], dp[str1Index - 1][str2Index - 1] + 1);
                }
            }
        }

        return dp[str1.length - 1][str2.length - 1];
    }
}
