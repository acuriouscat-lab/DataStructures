package algorithmlearning.class11;

import java.util.ArrayList;
import java.util.List;

public class Code03_PrintAllPermutations {
    //打印一个字符串的全部排列
    public static ArrayList<String> permutation(String str){
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chars = str.toCharArray();
        process1(res, chars, 0);
        return res;
    }

    /**
     * @param res   结果存放的集合
     * @param chars 存放的数组
     * @param index index之后该怎么放
     */
    private static void process1(ArrayList<String> res, char[] chars, int index) {
        if (index == chars.length) {
            res.add(String.valueOf(chars));
        }
        for (int i = index; i < chars.length; i++) {//index..之后  谁来到index位置上
            swap(chars, i, index);
            process1(res, chars, index + 1);
            swap(chars, i, index);
        }
    }

    private static void swap(char[] chars, int i, int index) {
        char temp = chars[i];
        chars[i] = chars[index];
        chars[index] = temp;
    }

    //打印一个字符串的全部排列，要求不要出现重复的排列
    public static ArrayList<String> permutationNoRepeat(String str){
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        String path = "";
        char[] arr = str.toCharArray();
        process2(res, 0, arr);
        return res;
    }
    //分支限界
    private static void process2(ArrayList<String> res, int index, char[] arr) {
        if (index == arr.length) {
            res.add(String.valueOf(arr));
            return;
        }
        boolean[] visited = new boolean[26];
        for (int i = index; i < arr.length; i++) {
            if (!visited[arr[i] - 'a']) {
                visited[arr[i]-'a'] = true;
                swap(arr, index, i);
                process2(res, index + 1, arr);
                swap(arr, index, i);
            }
        }
    }

    public static void main(String[] args) {
        String s = "aac";
        List<String> ans1 = permutation(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutationNoRepeat(s);
        for (String str : ans2) {
            System.out.println(str);
        }

    }


}
