package algorithmlearning.class12;

import java.util.HashMap;

public class Code02_StickersToSpellWord {

    /*
        给定一个字符串str，给定一个字符串类型的数组arr。
        arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
        返回需要至少多少张贴纸可以完成这个任务。
        例子：str= "babac"，arr = {"ba","c","abcd"}
        至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
     */
    public static int minStickers1(String[] stickers, String target) {
        int n = stickers.length;//贴纸的张数
        int[][] map = new int[n][26];
        // 将每张字符对应的词频放到 map 中 ===》 词袋
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                map[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        // "" 需要 0张
        dp.put("", 0);

        return process1(target, dp, map);
    }

    // 剩余 rest ，需要多少张
    private static int process1(String rest, HashMap<String, Integer> dp, int[][] map) {
        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }
        int ans = Integer.MAX_VALUE; // ans -> 搞定rest，使用的最少的贴纸数量
        int l = map.length;
        int[] tmap = new int[26];
        // 将当前 rest 说需要的词 放进 tmap
        char[] target = rest.toCharArray();
        for (char ch : target) {
            tmap[ch - 'a']++;
        }
        for (int i = 0; i < l; i++) {
            //先找到将头部的字节消掉的贴纸
            //  ===》 一种寻找策略
            if (map[i][target[0] - 'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {//a-z
                if (tmap[j] > 0) {
                    // 只要一张map[i][j]， 除去 tmap[j] - map[i][j]
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            String s = sb.toString();
            int temp = process1(s, dp, map);
            if (temp != -1) {
                ans = Math.min(ans, 1 + temp);
            }
        }
        dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }

    public static void main(String[] args) {
        String[] arr = {"aa","bb","ccddd"};
        String str = "aaaaaaaa";
        System.out.println(minStickers1(arr, str));
//        System.out.println(minStickers2(arr, str));


    }
}
