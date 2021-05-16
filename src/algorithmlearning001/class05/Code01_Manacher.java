package algorithmlearning001.class05;

public class Code01_Manacher {
    //假设字符串str长度为N，想返回最长回文子串的长度
    //时间复杂度O(N)
    /*
    1）理解回文半径数组
    2）理解所有中心的回文最右边界R，和取得R时的中心点C
    3）理解   L…(i`)…C…(i)…R  的结构，以及根据i’回文长度进行的状况划分
    4）每一种情况划分，都可以加速求解i回文半径的过程
     */
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int C = -1;//最大回文的中心
        int R = -1;//最大回文右边界的下一个
        int max = Integer.MIN_VALUE;
        char[] str = manacherString(s);//插入虚轴
        int[] pArr = new int[str.length];//记录每个位置的回文长度
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;//当前位置不用检验的长度为多少
            while (i + pArr[i] < str.length && i - pArr[i] >= 0) {//往外扩
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (R < i + pArr[i]) {//如果超出之前的最大范围  更新C和R
                C = i;
                R = i + pArr[i];
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;//半径需要减一  直径除以2
    }


    public static char[] manacherString(String str) {
        char[] strChar = str.toCharArray();
        char[] res = new char[strChar.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) != 0 ? strChar[index++] : '#';
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(manacher("babad"));
    }

//    // for test
//    public static int right(String s) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        char[] str = manacherString(s);
//        int max = 0;
//        for (int i = 0; i < str.length; i++) {
//            int L = i - 1;
//            int R = i + 1;
//            while (L >= 0 && R < str.length && str[L] == str[R]) {
//                L--;
//                R++;
//            }
//            max = Math.max(max, R - L - 1);
//        }
//        return max / 2;
//    }
//
//    // for test
//    public static String getRandomString(int possibilities, int size) {
//        char[] ans = new char[(int) (Math.random() * size) + 1];
//        for (int i = 0; i < ans.length; i++) {
//            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
//        }
//        return String.valueOf(ans);
//    }
//
//    public static void main(String[] args) {
//        int possibilities = 5;
//        int strSize = 20;
//        int testTimes = 5000000;
//        System.out.println("test begin");
//        for (int i = 0; i < testTimes; i++) {
//            String str = getRandomString(possibilities, strSize);
//            if (manacher(str) != right(str)) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("test finish");
//    }
//
//


}
