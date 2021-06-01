package greatoffer.class08;

import java.util.ArrayList;
import java.util.List;

public class Code04_RemoveInvalidParentheses {

    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[] { '(', ')' });
        return ans;
    }

    public static void  remove(String s, List<String> ans, int checkIndex, int modifyIndex, char[] par) {
        for (int stack = 0, i = checkIndex; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                stack++;
            }else{
                stack--;
            }
            if (stack < 0) {
                for (int j = modifyIndex; j <= i; j++) {
                    if (s.charAt(j) == par[1] && (j == modifyIndex || s.charAt(j - 1) != par[1])) {
                        remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
                    }
                }
                return;
            }
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            remove(reversed, ans, 0, 0, new char[] { ')', '(' });
        } else {
            ans.add(reversed);
        }
    }



}
