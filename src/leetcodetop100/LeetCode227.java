package leetcodetop100;

import java.util.LinkedList;

public class LeetCode227 {




    public int calculate(String s) {
        return -1;
    }

    public int[] process(char[] str, int index) {
        LinkedList<String> que = new LinkedList<>();
        int cur = 0;
        int i = index;
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
                continue;
            }
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i] - '0';
            } else if (str[i] != '(') {

            }
        }
        return null;
    }
//
//    public void addNum(LinkedList<String> que, int cur) {
//        if (!que.isEmpty()) {
//
//        }
//    }


}
