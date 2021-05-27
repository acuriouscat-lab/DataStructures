import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class test2 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));

//        HashMap<Character, Integer> map = new HashMap<>();
//        map.containsKey()

    }


        public static String longestPalindrome(String s) {
            if(s == null || s.length() == 0) return "";
            char[] arr = manacherString(s);
            int C = -1;
            int R = -1;
            int maxR = -1;
            int start = -1;
            int[] pArr = new int[arr.length];
            for(int i = 0 ;i < arr.length; i++){
                pArr[i] = R > i ? Math.min(R-i,pArr[2*C-i]):1;
                while(i + pArr[i] < arr.length && i - pArr[i] >=0){
                    if(arr[i + pArr[i]] == arr[i - pArr[i]]){
                        pArr[i]++;
                    }else{
                        break;
                    }
                }
                if( R < i + pArr[i]){
                    R = i + pArr[i];
                    C = i;
                }
                if( pArr[i] > maxR){
                    maxR = pArr[i];
                    start = (i - maxR +1) /2;
                }
            }
            return s.substring(start,start+maxR-1);
        }
        public static char[] manacherString(String str){
            int size = str.length();
            char[] res = new char[size*2 +1];
            int index = 0;
            for(int i = 0; i < res.length; i++){
                if((i&1) != 0 ){
                    res[i] = str.charAt(index++);
                }else{
                    res[i] = '#';
                }
            }
            return res;
        }

}



class Solution {
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int max = 1024;
        int[][] f = new int[k][max];
        int[] g = new int[k];
        for (int i = 0; i < k; i++) {
            Arrays.fill(f[i], 0x3f3f3f3f);
            g[i] = 0x3f3f3f3f;
        }
        for (int i = 0, cnt = 0; i < k; i++, cnt = 0) {
            // 使用 map 和 cnt 分别统计当前列的「每个数的出现次数」和「有多少个数」
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            if (i == 0) { // 第 0 列：只需要考虑如何将该列变为 xor 即可
                for (int xor = 0; xor < max; xor++) {
                    f[0][xor] = Math.min(f[0][xor], cnt - map.getOrDefault(xor, 0));
                    g[0] = Math.min(g[0], f[0][xor]);
                }
            } else { // 其他列：考虑与前面列的关系
                for (int xor = 0; xor < max; xor++) {
                    f[i][xor] = g[i - 1] + cnt; // 整列替换
                    for (int cur : map.keySet()) { // 部分替换
                        f[i][xor] = Math.min(f[i][xor], f[i - 1][xor ^ cur] + cnt - map.get(cur));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }
        }
        return f[k - 1][0];
    }

}

class Solution1 {
    public int minChanges(int[] nums, int k) {
        int n = nums.length, m = 1024;
        int[] prev = new int[m];
        int prevMin = Integer.MAX_VALUE;
        for (int i = 0; i < k; ++i) {// O(K)
            int total = 0;
            int[] counter = new int[m];
            for (int l = i; l < n; l += k, ++total) ++counter[nums[l]];

            if (i == 0) {
                for (int j = 0; j < m; ++j) {
                    prev[j] = total - counter[j];
                    prevMin = Math.min(prevMin, prev[j]);
                }
            } else {
                int[] curr = new int[m];
                int currMin = Integer.MAX_VALUE;
                for (int j = 0; j < m; ++j) {// O(1024)
                    curr[j] = prevMin + total;
                    for (int l = i; l < n; l += k) {// O(N/K)
                        curr[j] = Math.min(curr[j], prev[j ^ nums[l]] + total - counter[nums[l]]);
                    }
                    currMin = Math.min(currMin, curr[j]);
                }
                prev = curr;
                prevMin = currMin;
            }
        }
        return prev[0];
    }
}