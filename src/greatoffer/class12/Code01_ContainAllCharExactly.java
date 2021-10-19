package greatoffer.class12;

// 本题测试链接 : https://leetcode-cn.com/problems/permutation-in-string/
public class Code01_ContainAllCharExactly {

    //给定长度为m的字符串aim，以及一个长度为n的字符串str
    //问能否在str中找到一个长度为m的连续子串，
    //使得这个子串刚好由aim的m个字符组成，顺序无所谓，
    //返回任意满足条件的一个子串的起始位置，未找到返回-1
    public static int containExactly3(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }
        char[] str2 = s2.toCharArray();
        int M = str2.length;
        int[] map = new int[256];
        for (char str : str2) {
            map[str]++;
        }
        int all = M;
        int R = 0;
        for (; R < M; R++) {
            if (map[s1.charAt(R)]-- > 0) {
                all--;
            }
        }
        for(;R < s1.length(); R++){
            if(all == 0){
                return R - M;
            }
            if (map[s1.charAt(R)]-- > 0) {
                all--;
            }
            if (map[s1.charAt(R - M)]++ >= 0) {
                all++;
            }
        }
        return all == 0 ? R - M : -1;
    }
}
