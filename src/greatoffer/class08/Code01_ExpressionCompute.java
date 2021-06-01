package greatoffer.class08;

import java.util.LinkedList;

public class Code01_ExpressionCompute {

    //给定一个字符串str，str表示一个公式，
    //公式里可能有整数、加减乘除符号和左右括号
    //返回公式的计算结果，难点在于括号可能嵌套很多层
    //str="48*((70-65)-43)+8*1"，返回-1816。
    //str="3+1*4"，返回7。
    //str="3+(1*4)"，返回7。
    //【说明】
    //1.可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
    //2.如果是负数，就需要用括号括起来，比如“4*(-3)”但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"都是合法的。
    //3.不用考虑计算过程中会发生溢出的情况。
    public static int getValue(String s) {
        return process(s.toCharArray(), 0)[0];
    }

    public static int[] process(char[] str,int index){
        LinkedList<String> queue = new LinkedList<>();
        int[] bra = null;
        int i = index;
        int cur = 0;
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            }else if(str[i] != '('){
                addNum(queue, cur);
                queue.addLast(String.valueOf(str[i++]));
                cur = 0;
            }else{
                bra = process(str, i + 1);
                i = bra[1] + 1;
                cur = bra[0];
            }
        }
        addNum(queue, cur);
        return new int[]{getNum(queue), i};
    }

    public static int getNum(LinkedList<String> queue) {
        int res = 0;
        boolean add = true;
        int num = 0;
        String cur = null;
        while (!queue.isEmpty()) {
            cur = queue.pollFirst();
            if (cur.equals("+") ) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            }else{
                num = Integer.parseInt(cur);
                res += add ? num : (-num);
            }

        }
        return res;
    }

    public static void addNum(LinkedList<String> queue, int cur) {
        if (!queue.isEmpty()) {
            String ch = queue.pollLast();
            int num = 0;
            if (ch.equals("+") || ch.equals("-")) {
                queue.add(ch);
            }else{
                Integer poll = Integer.valueOf(queue.pollLast());
                if (ch.equals("*")) {
                    cur = cur * poll;
                }else{
                    cur = poll / cur;
                }
            }
        }
        queue.addLast(String.valueOf(cur));
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));

        exp = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(getValue(exp));

    }


}
