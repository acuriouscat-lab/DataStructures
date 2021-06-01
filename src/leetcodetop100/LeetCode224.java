package leetcodetop100;

import java.util.LinkedList;

public class LeetCode224 {

    /*
    1 <= s.length <= 3 * 105
    s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
    s 表示一个有效的表达式
     */
    public int calculate(String s) {
        char[] str = s.toCharArray();
        return process(s.toCharArray(), 0)[1];
    }


    public int[] process(char[] str, int index) {
        int ans = 0;
        int i = index;
        int cur = 0;
        int[] bra = null;
        LinkedList<String> que = new LinkedList<>();
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
                continue;
            }
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') {
                que.addLast(String.valueOf(cur));
                que.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else {
                bra = process(str, i + 1);
                i = bra[0] + 1;
                cur = bra[1];
            }
        }
        que.add(String.valueOf(cur));
        int num = getNum(que);
        return new int[]{i, num};
    }


    public int getNum(LinkedList<String> que) {
        int ans = 0;
        int cur = 0;
        boolean add = true;
        while (!que.isEmpty()) {
            String poll = que.pollFirst();
            if (poll.equals("+")) {
                add = true;
            } else if (poll.equals("-")) {
                add = false;
            } else {
                cur = Integer.parseInt(poll);
                ans += add ? cur : (-cur);
            }
        }
        return ans;
    }
}
