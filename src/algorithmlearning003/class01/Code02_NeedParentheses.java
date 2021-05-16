package algorithmlearning003.class01;

public class Code02_NeedParentheses {

    //括号有效配对是指：
    //1）任何一个左括号都能找到和其正确配对的右括号
    //2）任何一个右括号都能找到和其正确配对的左括号
    //有效的：    (())  ()()   (()())  等
    //无效的：     (()   )(     等

    //问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
    /*
        当遇到左括号
                count++  需要右括号的数量
        当遇到右括号
                如果count == 0  ==>   左右括号数量相同
                否则need++      右括号比较多
     */
    public static int needParentheses(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;//需要几个右括号
        int need = 0;//需要几个左括号
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if(str[i] == '('){
                count ++;
            }else{
                if(count == 0){//当右括号比左括号多的时候
                    need ++;
                }else{
                    count --;
                }
            }
        }
        return count + need;
    }



}
