package algorithmlearning003.class03;

public class Code05_LCSubstring {

    public static String lcst1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int end = 0;
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);


    }
    public static int[][] getdp(char[] str1,char[] str2){
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : 0;
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : 0;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }

    public String LCS (String str1, String str2) {
        // write code here
        if(str1 == null || str2 == null){
            return "";
        }
        String result = "";
        int start = 0;
        int end = 1;
        while(end <= str2.length()){
            String subStr = str2.substring(start,end);
            if(str1.contains(subStr)){
                result = subStr;
            }else{
                start++;
            }
            end++;
        }
        return result;
    }

    public String LCS2 (String str1, String str2) {
        // write code here
        int m = str1.length(),n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        int max = 0,index = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    dp[i+1][j + 1] = dp[i][j] + 1;
                    if(max < dp[i + 1][j + 1]){
                        max = dp[i + 1][j + 1];
                        index = i + 1;
                    }
                }
            }
        }
        return max == 0 ? "" : str1.substring(index - max,index);
    }

    public static String lcst2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = 0; // 出发点的行号
        int col = chs2.length - 1; // 出发点的列号
        int max = 0;
        int end = 0;
        while (row < chs1.length) {
            int i = row;
            int j = col;
            int len = 0;
            // 向右下方移动的这一轮
            while (i < chs1.length && j < chs2.length) {
                if (chs1[i] != chs2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                if (len > max) {
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
        System.out.println(lcst1(str1, str2));
        System.out.println(lcst2(str1, str2));

    }



}
