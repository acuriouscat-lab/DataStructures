import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Code02_ContainAllCharExactly {

    public static int containExactly1(String s, String a) {
        if (s == null || a == null || s.length() < a.length()) {
            return -1;
        }
        char[] aim = a.toCharArray();
        Arrays.sort(aim);
        String aimSort = String.valueOf(aim);
        for (int L = 0; L < s.length(); L++) {
            for (int R = L; R < s.length(); R++) {
                char[] cur = s.substring(L, R + 1).toCharArray();
                Arrays.sort(cur);
                String curSort = String.valueOf(cur);
                if (curSort.equals(aimSort)) {
                    return L;
                }
            }
        }
        return -1;
    }

    public static int containExactly2(String s, String a) {
        if (s == null || a == null || s.length() < a.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] aim = a.toCharArray();
        for (int L = 0; L <= str.length - aim.length; L++) {
            if (isCountEqual(str, L, aim)) {
                return L;
            }
        }
        return -1;
    }

    public static boolean isCountEqual(char[] str, int L, char[] aim) {
        int[] count = new int[256];
        for (int i = 0; i < aim.length; i++) {
            count[aim[i]]++;
        }
        for (int i = 0; i < aim.length; i++) {
            if (count[str[L + i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    //给定长度为m的字符串aim，以及一个长度为n的字符串str
    //问能否在str中找到一个长度为m的连续子串，
    //使得这个子串刚好由aim的m个字符组成，顺序无所谓，
    //返回任意满足条件的一个子串的起始位置，未找到返回-1
    public static int containExactly3(String s, String a) {
        if (s == null || a == null || s.length() < a.length()) {
            return -1;
        }
        int[] count = new int[256];
        char[] arr = a.toCharArray();
        char[] sArr = s.toCharArray();
        int M = arr.length;
        for (int i = 0; i < M; i++) {
            count[arr[i]]++;
        }
        int R = 0;
        int inValidTime = 0;
        for (; R < M; R++) {
            if (count[sArr[R]]-- <= 0) {
                inValidTime++;
            }
        }
        for (; R < sArr.length; R++) {
            if (inValidTime == 0) {
                return R - M;
            }
            if (count[sArr[R]]-- <= 0) {
                inValidTime++;
            }
            if (count[sArr[R - M]]++ < 0) {
                inValidTime--;
            }
        }
        return inValidTime == 0 ? R - M : -1;
    }
    // for test
    public static String getRandomString(int possibilities, int maxSize) {
        char[] ans = new char[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 20;
        int aimMaxSize = 5;
        int testTimes = 500000;
        System.out.println("test begin, test time : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strMaxSize);
            String aim = getRandomString(possibilities, aimMaxSize);
            int ans1 = containExactly1(str, aim);
            int ans2 = containExactly2(str, aim);
            int ans3 = containExactly3(str, aim);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
        HashMap<String, ArrayList<String>> map=new HashMap<>();
    }
}
