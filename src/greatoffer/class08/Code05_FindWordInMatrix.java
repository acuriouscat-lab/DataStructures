package greatoffer.class08;

/*
 * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
 * 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
 * 比如：
 * char[][] m = {
 *     { 'a', 'b', 'z' },
 *     { 'c', 'd', 'o' },
 *     { 'f', 'e', 'o' },
 * };
 * word = "zooe"
 * 是可以找到的
 *
 * 设定1：可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
 *
 * 设定2：不可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
 *
 * 写出两种设定下的code
 *
 * */
public class Code05_FindWordInMatrix {

    // 可以走重复的设定
    public static boolean findWord1(char[][] m, String word) {

        int N = m.length;
        int M = m[0].length;
        char[] str = word.toCharArray();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (process(m, i, j, 0, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean process(char[][] m, int i, int j, int index, char[] word) {
        if (index == word.length) {
            return true;
        }
        if (i < 0 || i == m.length || j == m[0].length || j < 0 || m[i][j] != word[index]) {
            return false;
        }
        boolean up = process(m, i + 1, j, index + 1, word);
        boolean down = process(m, i - 1, j, index + 1, word);
        boolean left = process(m, i, j - 1, index + 1, word);
        boolean right = process(m, i, j + 1, index + 1, word);
        return up || down || left || right;
    }


    // 可以走重复的设定
    public static boolean findWord12(char[][] m, String word) {

        int N = m.length;
        int M = m[0].length;
        char[] str = word.toCharArray();
        int len = str.length;
        // dp[i][j][k]表示：必须以m[i][j]这个字符结尾的情况下，能不能找到w[0...k]这个前缀串
        boolean[][][] dp = new boolean[N][M][len];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = m[i][j] == str[0];
            }
        }
        for (int k = 1; k < len; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][k] = (m[i][j] == str[k] && checkPrevious(dp, i, j, k));
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j][len - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkPrevious(boolean[][][] dp, int i, int j, int k) {
        boolean down = i < dp.length - 1 && dp[i + 1][j][k - 1];
        boolean up = i > 0 && dp[i - 1][j][k - 1];
        boolean left = j > 0 && dp[i][j - 1][k - 1];
        boolean right = j < dp[0].length -1 && dp[i][j + 1][k - 1];
        return down || up || left || right;
    }


    // 不可以走重复路的设定
    public static boolean findWord2(char[][] m, String word) {
        if (word == null || word.equals("")) {
            return true;
        }
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }
        int N = m.length;
        int M = m[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (process2(m, i, j, 0, word.toCharArray())) {
                    return true;
                }
            }
        }
        return false;

    }


    public static boolean process2(char[][] m, int i, int j, int index, char[] str) {
        if (index == str.length) {
            return true;
        }
        if (i < 0 || i == m.length || j == m[0].length || j < 0 || m[i][j] != str[index]) {
            return false;
        }
        m[i][j] = 0;
        boolean down = process2(m, i + 1, j, index + 1, str);
        boolean up = process2(m, i - 1, j, index + 1, str);
        boolean left = process2(m, i, j - 1, index + 1, str);
        boolean right = process2(m, i, j + 1, index + 1, str);
        boolean res = down || up || left || right;
        m[i][j] = str[index];
        return res;
    }

    public static void main(String[] args) {
        char[][] m = { { 'a', 'b', 'z' },
                    { 'c', 'd', 'o' },
                    { 'f', 'e', 'o' }, };
        String word1 = "zoooz";
        String word2 = "zoo";
        // 可以走重复路的设定
        System.out.println(findWord1(m, word1));
        System.out.println(findWord12(m, word1));
        System.out.println(findWord1(m, word2));
        System.out.println(findWord12(m, word2));
        // 不可以走重复路的设定
        System.out.println(findWord2(m, word1));
        System.out.println(findWord2(m, word2));

    }
}