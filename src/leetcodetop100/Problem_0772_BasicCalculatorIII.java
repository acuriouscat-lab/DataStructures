package leetcodetop100;

import java.util.LinkedList;

public class Problem_0772_BasicCalculatorIII {

    public static void main(String[] args) {
        System.out.println(calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));
        System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
    }

    public static int calculate(String s) {
        return f(s.toCharArray(), 0)[0];
    }

    /**
     * @param str   要处理的字符串
     * @param index 当前来到index位置，后面结果为过少
     * @return index..到末尾或者到右括号为止的结果
     */
    public static int[] f(char[] str, int i) {
        int[] ans = null;
        int cur = 0;
        LinkedList<String> que = new LinkedList<>();
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
                continue;
            }
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到运算符了
                addNum(que, cur);
                que.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 碰到左括号
                ans = f(str, i + 1);
                cur = ans[0];
                i = ans[1] + 1;
            }
        }
        addNum(que, cur);
        return new int[]{getNum(que), i};
    }

    // 队列里都是加减法  返回结果
    private static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        int num = 0;
        String cur = null;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur != null) {
                if (cur.equals("+")) {
                    add = true;
                } else if (cur.equals("-")) {
                    add = false;
                } else {
                    num = Integer.valueOf(cur);
                    res += add ? num : -num;
                }
            }
        }
        return res;
    }

    public static void addNum(LinkedList<String> que, int num) {
        // 判断是否为空，避免一开始就是运算符的情况：-3*4
        if (!que.isEmpty()) {
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                Integer cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? num * cur : cur / num;
            }
        }
        que.addLast(String.valueOf(num));
    }

}
