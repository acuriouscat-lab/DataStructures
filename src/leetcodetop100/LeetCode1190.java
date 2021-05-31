package leetcodetop100;

public class LeetCode1190 {

    /*
    输入：s = "(u(love)i)"
    输出："iloveu"
     */


    public static String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        char[] str = s.toCharArray();
        int index = 0;
        while (index < str.length) {
            if (str[index] == '(') {
                index = process(str, index + 1);
            }
            index++;
        }
        for (int i = 0; i < str.length; i++) {
            if (str[i] != ')' && str[i] != '(') {
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }


    public static int process(char[] str,int L){
        int index = L;
        while (index < str.length && str[index] != ')') {
            if (str[index] != '(' ) {
                index++;
            }else if(str[index] == '('){
                index = process(str,index + 1) + 1;
            }
        }
        reverse(str,L,index - 1);
        return index;
    }

    public static void reverse(char[] str, int L, int R) {
        while (L <= R) {
            char temp = str[L];
            str[L] = str[R];
            str[R] = temp;
            R--;
            L++;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseParentheses("f(ul)ao(t(y)qbn)()sj"));//"fluaonbqytsj"
    }


}
