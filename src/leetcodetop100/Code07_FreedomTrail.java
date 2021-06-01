package leetcodetop100;

import java.util.ArrayList;
import java.util.HashMap;

public class Code07_FreedomTrail {

    public int findRotateSteps(String ring, String key) {
        char[] strs = ring.toCharArray();
        HashMap<Character, ArrayList<Integer>> map = new HashMap<Character, ArrayList<Integer>>();
        for (int i = 0; i < strs.length; i++) {
            char str = strs[i];
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<>());
            }
            map.get(str).add(i);
        }
        int N = strs.length;
        int[][] dp = new int[N ][key.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process(map, key.toCharArray(), 0, 0, N,dp);
    }

    public int process(HashMap<Character, ArrayList<Integer>> map, char[] key, int index, int preIndex, int N,int[][] dp) {
        if (dp[preIndex][index] != -1) {
            return dp[preIndex][index];
        }
        if (index == key.length) {
            return 0;
        }
        char cur = key[index];
        ArrayList<Integer> nexts = map.get(cur);
        int ans = Integer.MAX_VALUE;
        for (Integer next : nexts) {
            int nextStep = dial(next, preIndex, N) + process(map, key, index + 1, next, N,dp) + 1;
            ans = Math.min(ans, nextStep);
        }
        dp[preIndex][index] = ans;
        return ans;
    }

    public int dial(int i1, int i2, int size) {
        return Math.min(Math.abs(i1 - i2), Math.min(i1, i2) + size - Math.max(i1, i2));
    }




}
