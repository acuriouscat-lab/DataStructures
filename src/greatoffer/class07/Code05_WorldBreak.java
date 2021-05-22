package greatoffer.class07;

import java.util.Arrays;
import java.util.HashSet;

public class Code05_WorldBreak {

    public static int ways1(String str, String[] arr) {
        HashSet<String> set = new HashSet<>(Arrays.asList(arr));
        return process(str, 0, set);
    }

    public static int process(String str, int index, HashSet<String> set) {
        if (index == str.length()) {
            return 1;
        }
        int ans = 0;
        for (int end = index; end < str.length(); end++) {
            String next = str.substring(index, end + 1);
            if (set.contains(next)) {
                ans += process(str, end + 1, set);
            }
        }
        return ans;
    }


    public static int ways2(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        HashSet<String> map = new HashSet<>(Arrays.asList(arr));
        int N = str.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                if (map.contains(str.substring(i, end + 1))) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    public static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }

//    public static int way3(String str, String[] arr) {
//        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
//            return 0;
//        }
//    }

    public static int ways4(String s, String[] arr) {
        if (s == null || s.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        Node root = new Node();
        for (String str : arr) {
            char[] chs = str.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                int path = str[end] - 'a';
                if (cur.nexts[path] == null) {
                    break;
                }
                cur = cur.nexts[path];
                if (cur.end) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    // 以下的逻辑都是为了测试
    public static class RandomSample {
        public String str;
        public String[] arr;

        public RandomSample(String s, String[] a) {
            str = s;
            arr = a;
        }
    }

    // 随机样本产生器
    public static RandomSample generateRandomSample(char[] candidates, int num, int len, int joint) {
        String[] seeds = randomSeeds(candidates, num, len);
        HashSet<String> set = new HashSet<>();
        for (String str : seeds) {
            set.add(str);
        }
        String[] arr = new String[set.size()];
        int index = 0;
        for (String str : set) {
            arr[index++] = str;
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < joint; i++) {
            all.append(arr[(int) (Math.random() * arr.length)]);
        }
        return new RandomSample(all.toString(), arr);
    }

    public static String[] randomSeeds(char[] candidates, int num, int len) {
        String[] arr = new String[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            char[] str = new char[(int) (Math.random() * len) + 1];
            for (int j = 0; j < str.length; j++) {
                str[j] = candidates[(int) (Math.random() * candidates.length)];
            }
            arr[i] = String.valueOf(str);
        }
        return arr;
    }

    public static void main(String[] args) {
        char[] candidates = { 'a', 'b' };
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 30000;
        boolean testResult = true;
        for (int i = 0; i < testTimes; i++) {
            RandomSample sample = generateRandomSample(candidates, num, len, joint);
            int ans1 = ways1(sample.str, sample.arr);
            int ans2 = ways2(sample.str, sample.arr);
            //int ans3 = ways3(sample.str, sample.arr);
            int ans4 = ways4(sample.str, sample.arr);
            if (ans1 != ans2  || ans2 != ans4) {
                testResult = false;
            }
        }
        System.out.println(testTimes + "次随机测试是否通过：" + testResult);
    }


}
