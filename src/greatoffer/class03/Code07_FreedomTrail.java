package greatoffer.class03;

import java.util.ArrayList;
import java.util.HashMap;

public class Code07_FreedomTrail {
    //https://leetcode-cn.com/problems/freedom-trail/
    /*
        电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
        给定一个字符串ring，表示刻在外环上的编码；给定另一个字符串key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
        最初，ring的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使key的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完key中的所有字符。
        旋转ring拼出 key 字符key[i]的阶段中：
        您可以将ring顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串ring的一个字符与 12:00 方向对齐，并且这个字符必须等于字符key[i] 。
        如果字符key[i]已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作1 步。按完之后，您可以开始拼写key的下一个字符（下一阶段）, 直至完成所有拼写
     */
    public int findRotateSteps(String ring, String key) {

        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        char[] strs = ring.toCharArray();
        // ring 中字符在哪些位置有
        for (int i = 0; i < strs.length; i++) {
            if (!map.containsKey(strs[i])) {
                map.put(strs[i], new ArrayList<>());
            }
            map.get(strs[i]).add(i);
        }
        int[][] dp = new int[strs.length][key.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process1(map, 0, 0, key.toCharArray(), strs.length, dp);
    }

    // arrs[index] 及其往后需要的步数
    // 上一步在 原始的 ring 的 第 preIndex
    public static int process1(HashMap<Character, ArrayList<Integer>> map, int preIndex, int index, char[] arrs, int N, int[][] dp) {
        if (dp[preIndex][index] != -1) {
            return dp[preIndex][index];
        }
        int ans = Integer.MAX_VALUE;
        if (index == arrs.length) {
            return 0;
        } else {
            char cur = arrs[index];
            ArrayList<Integer> curIndex = map.get(cur);

            for (Integer next : curIndex) {
                int cost = process1(map, next, index + 1, arrs, N, dp) + dial(next, preIndex, N) + 1;
                ans = Math.min(ans, cost);
            }
        }
        dp[preIndex][index] = ans;
        return ans;
    }

    public static int dial(int i1, int i2, int size) {
        return Math.min(Math.abs(i2 - i1), Math.min(i1, i2) + size - Math.max(i2, i1));
    }


}
