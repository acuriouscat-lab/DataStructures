package algorithmlearning.class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code02_PrintAllSubsequences {
    //打印一个字符串的全部子序列
    public static List<String> subs(String s) {
        char[] chars = s.toCharArray();
        String path = "";
        List<String> res = new ArrayList<>();
        process1(chars,res, 0, path);
        return res;
    }

    private static void process1(char[] chars, List<String> res, int index, String path) {
        if (index == chars.length) {
            res.add(path);
            return;
        }
        String no = path;
        process1(chars, res, index + 1, path);
        String yes = path + String.valueOf(chars[index]);
        process1(chars, res, index + 1, yes);
    }

    public static List<String> subsNoRepeat(String s) {
        char[] chars = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        String path = "";
        process2(chars, set, 0, path);
        return new ArrayList<>(set);
    }

    private static void process2(char[] chars, HashSet<String> set, int index, String path) {
        if (index == chars.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(chars, set, index + 1, path);
        String yes = path + chars[index];
        process2(chars, set, index + 1, yes);
    }

    public static void main(String[] args) {
        String test = "aacc";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }

}
