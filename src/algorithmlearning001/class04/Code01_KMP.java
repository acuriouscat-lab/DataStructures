package algorithmlearning001.class04;

public class Code01_KMP {

    //假设字符串str长度为N，字符串match长度为M，M <= N
    //想确定str中是否有某个子串是等于match的。
    //时间复杂度O(N)
    private static int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < m.length() || m.length() < 1) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int p1 = 0;                     //strs中当前比对来到的位置
        int p2 = 0;                     //match中当前比对到的位置
        int[] next = getNextArray(str2);//next[i] match中i之前的字符串match[0....i-1]
        while (p1 < str1.length && p2 < str2.length) {
            if (str1[p1] == str2[p2]) {
                p1++;
                p2++;
            } else if (next[p2] == -1) {//y已经来到0位置 ===> y == 0
                p1++;
            }else {
                p2 = next[p2];//当前位置不匹配 跳转到i-1之前的最长前后缀长度
            }
        }
        // 1） p1 越界 p2 没有 -1
        // 2） p1 没有 p2越界了
        // 3） 都越界
        // ===》 p2越界了 就存在
        return p2 == m.length() ? p1 - p2 : -1;
    }

    //o(M)
    // next[i] 表示 和 i 无关，之前的最大前缀和后缀相等的长度
    private static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        // 默认
        next[0] = -1;
        next[1] = 0;
        //i-1 位置的最长后缀
        // cn代表，cn位置的字符，是当前和i-1位置比较的字符 也是 需要跳转到的位置
        int cn = 0;
        int i = 2;
        while (i < ms.length) {
            if (ms[i - 1] == ms[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            }else{
                i++;
            }
        }
        return next;
    }


    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
