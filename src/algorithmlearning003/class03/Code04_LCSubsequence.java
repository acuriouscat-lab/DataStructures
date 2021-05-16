package algorithmlearning003.class03;

public class Code04_LCSubsequence {

    /*
        请注意区分子串和子序列的不同，给定两个字符串str1和str2，
            求两个字符的最长公共子序列
            动态规划的空间压缩技巧！
     */

    public static String lcse(String str1, String str2){
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1,chs2);
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];//dp[m][n] 为最大长度
        int index = res.length - 1;
        while(index >= 0){//从后往前一次比较
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n --;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m --;
            }else{//如果不相等 说明该位置是以 m 和 n 为结尾的公共子序列
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static int[][] getdp(char[] text1,char[] text2){
        int[][] dp = new int[text1.length][text2.length];
        dp[0][0] = text1[0] == text2[0] ? 1 : 0;
        for(int i = 1; i < dp.length; i++){//第一列
            dp[i][0] = Math.max(dp[i - 1][0], text1[i] == text2[0] ? 1 : 0);
        }
        for(int i = 1; i < dp[0].length; i++){//第一行
            dp[0][i] = Math.max(dp[0][i - 1], text1[0] == text2[i] ? 1 : 0);
        }
        for(int i = 1; i < dp.length; i++){//任意位置
            for (int j = 1; j < dp[0].length; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);//不已当前i j 为结尾
                if(text1[i] == text2[j]){//以 i j 为结尾的话
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }


    public String LCS (String s1, String s2) {
        // write code here
        if(s1==null || s1.length()==0 || s2==null || s2.length()==0)return "-1";
        int s1L = s1.length()-1;
        int s2L = s2.length()-1;
        StringBuilder str = new StringBuilder();
        while(s1L >= 0 && s2L >= 0){
            if(s1.charAt(s1L)==s2.charAt(s2L)){
                str.append(s1.charAt(s1L));
                s1L--;
                s2L--;
            }else{
                if (s1L>= 1 && s2L>= 1) {
                    if (Math.max(s1.charAt(s1L - 1), s2.charAt(s2L)) >= Math.max(s1.charAt(s1L), s2.charAt(s2L - 1)))
                        s2L--;
                    else
                        s1L--;
                }else if (s1L==0){
                    s2L--;
                }else
                    s1L--;
            }
        }
        if(str.length()==0)return "-1";
        return str.reverse().toString();

    }

    public static void main(String[] args) {
        String str1 = "A1BC2D3EFGH45I6JK7LMN";
        String str2 = "12OPQ3RST4U5V6W7XYZ";
        System.out.println(lcse(str1, str2));

    }


}
