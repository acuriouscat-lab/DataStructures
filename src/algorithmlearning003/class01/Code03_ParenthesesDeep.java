package algorithmlearning003.class01;

public class Code03_ParenthesesDeep {
    //括号有效配对是指：
    //1）任何一个左括号都能找到和其正确配对的右括号
    //2）任何一个右括号都能找到和其正确配对的左括号
    //有效的：    (())  ()()   (()())  等
    //无效的：     (()   )(     等
    //问题一：怎么判断一个括号字符串有效？
    //问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效

    public static boolean isVaild(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int count = 0;//表示需要右括号的数量 ==》  遇到左括号的时候 ++  遇到右括号的时候 --
        for (int i = 0; i < str.length; i++) {//当遇到左括号count+1 右括号count -1 中途如果有负数  直接为false 最后看是否为0
            count += str[i] == '(' ? 1 : -1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    //括号有效配对是指：
    //1）任何一个左括号都能找到和其正确配对的右括号
    //2）任何一个右括号都能找到和其正确配对的左括号
    //返回一个括号字符串中，最长的括号有效子串的长度
    //  dp[i]  以i结尾的最长有小括号为多少？
    //从左往右的尝试模型
    /*
            当遇到右括号的时候才有可能是有效的括号
                    遇到右括号的时候
                    ‘   调到上一个需要判断的位置
     */
    public static int maxLength(String s) {
        if (s == null || s.length() < 2) return 0;
        int pre = 0;
        int res = 0;
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        for (int i = 0; i < dp.length; i++) {
            if (str[i] == ')') {
                pre = i - dp[i - 1] - 1;//调到需要判断的位置
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = 2 + dp[i - 1] + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    //在一个有效的括号字符串中，判断括号的最大深度
    private static int deep(String s) {
        char[] str = s.toCharArray();
        if (!isVaild(str)) {
            return 0;
        }
        int res = 0;
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                res = Math.max(res, ++count);
            } else {
                count--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String test = "((()))";
        System.out.println(deep(test));

    }


}
