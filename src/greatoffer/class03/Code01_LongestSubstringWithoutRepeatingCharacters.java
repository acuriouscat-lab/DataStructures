package greatoffer.class03;

public class Code01_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        // map[i] 表示 i 上一次出现的位置是哪里
        int[] map = new int[256];

        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        map[str[0]] = 0;
        int N = str.length;
        int ans = 1;
        int pre = 1;
        for (int i = 1; i < N; i++) {
            // 上一次能扩出来的最远，和当前字符上一次出现的距离 取最小
            pre = Math.min(i - map[str[i]], pre + 1);
            ans = Math.max(ans, pre);
            map[str[i]] = i;
        }
        return ans;
    }


}
