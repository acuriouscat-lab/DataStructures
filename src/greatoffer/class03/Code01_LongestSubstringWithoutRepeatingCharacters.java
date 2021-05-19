package greatoffer.class03;

public class Code01_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] strs = s.toCharArray();
        int[] map = new int[256];
        int N = strs.length;
        for (int i = 0; i < N; i++) {
            map[strs[i] - 'a'] = -1;
        }
        map[strs[0] - 'a'] = 0;
        int pre = 1;
        int ans = 1;
        for (int i = 1; i < N; i++) {
            pre = Math.min(pre + 1, i - map[strs[i] - 'a']);
            ans = Math.max(pre, ans);
            map[strs[i] - 'a'] = i;
        }
        return ans;
    }


}
