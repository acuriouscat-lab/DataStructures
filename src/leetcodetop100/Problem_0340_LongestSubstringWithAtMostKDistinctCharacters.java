package leetcodetop100;

public class Problem_0340_LongestSubstringWithAtMostKDistinctCharacters {

    /*
    给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
    假设所有字符都是小写字母
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }
        char[] str = s.toCharArray();
        int R = 0;
        int[] count = new int[256];
        int need = 0;
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            while (R < str.length && (need < k || (need == k && count[str[R] - 'a'] > 0))) {
                need = count[str[i] - 'a'] == 0 ? 1 : 0;
                count[str[R++] - 'a']++;
            }
            ans = Math.max(ans, R - i);
            if (count[str[i] - 'a'] == 1) {
                need--;
            }
            count[str[i] - 'a']--;
        }
        return ans;
    }


}
