package algorithmlearning.class09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
//贪心算法
public class Code01_LowestLexicography {

    /*
            给定一个由字符串组成的数组strs，
            必须把所有的字符串拼接起来，
            返回所有可能的拼接结果中，字典序最小的结果
     */
    public static String lowestString1(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }
        HashSet<Integer> use = new HashSet<>();
        ArrayList<String> all = new ArrayList<>();
        process(strs, use, all, "");
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (lowest.compareTo(all.get(i))>0) {
                lowest = all.get(i);
            }
        }
        return lowest;
    }

    /**
     * 深度优先遍历
     * @param strs  所有的字符串
     * @param use   已经 使用过的字符在strs里的下标
     * @param all   全部的字符串
     * @param path  拼接的字符串
     */
    private static void process(String[] strs, HashSet<Integer> use, ArrayList<String> all, String path) {
        if (use.size() == strs.length) {
            all.add(path);
        }else{
            for (int i = 0; i < strs.length; i++) {
                if (!use.contains(i)) {
                    use.add(i);
                    process(strs, use, all, path + strs[i]);
                    use.remove(i);
                }
            }
        }
    }

    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString2(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }

        Arrays.sort(strs, new MyComparator());

        String str = "";
        for (int i = 0; i < strs.length; i++) {
            str += strs[i];
        }
        return str;
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 100000;
        String[] arr = generateRandomStringArray(arrLen, strLen);
        System.out.println("先打印一个生成的字符串");
        for (String str : arr) {
            System.out.print(str + ",");
        }
        System.out.println();
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }



}
