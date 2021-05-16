package guigu.tenalgorithm;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCGAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext("ABCDABD ");
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1, str2, next));

    }

    //kmp algorithm

    /**
     *
     * @param str1  大串
     * @param str2  小串
     * @param next  小窗的部分匹配表
     * @return  有就返回第一个匹配的位置 否则为-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //需要处理
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i-j+1;
            }
        }
        return  -1;
    }

    //获取到一个字符串的部分匹配表
    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) 我们需要从next[j-1]获取新的J
            //直到我们发现有dest.charAt(i) == dest.charAt(j)才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //dest.charAt(i) != dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
