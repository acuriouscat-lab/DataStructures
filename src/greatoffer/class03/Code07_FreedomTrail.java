package greatoffer.class03;

import java.util.ArrayList;
import java.util.HashMap;

public class Code07_FreedomTrail {
    //https://leetcode-cn.com/problems/freedom-trail/
    public int findRotateSteps(String ring, String key) {

        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        char[] strs = ring.toCharArray();
        for (int i = 0; i < strs.length; i++) {
            if (!map.containsKey(strs[i])) {
                map.put(strs[i], new ArrayList<>());
            }
            map.get(strs[i]).add(i);
        }
        int[][] dp = new int[strs.length][key.length() + 1];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                dp[i][j] = -1;
            }
        }
        return process1(map, 0, 0, key.toCharArray(), strs.length,dp);
    }


    public static int process1(HashMap<Character, ArrayList<Integer>> map, int preIndex, int index, char[] arrs,int N,int[][] dp) {
        if(dp[preIndex][index] != -1){
            return dp[preIndex][index];
        }
        int ans = Integer.MAX_VALUE;
        if (index == arrs.length) {
            return 0;
        }else{
            char cur = arrs[index];
            ArrayList<Integer> curIndex = map.get(cur);

            for (Integer next : curIndex) {
                int cost = process1(map, next, index + 1, arrs, N,dp) + dial(next, preIndex, N) + 1;
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
