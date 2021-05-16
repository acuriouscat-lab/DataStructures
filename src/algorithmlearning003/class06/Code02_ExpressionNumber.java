package algorithmlearning003.class06;

public class Code02_ExpressionNumber {


    //给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成 的字符串express，再给定一个布尔值 desired。返回express能有多少种组合 方式，可以达到desired的结果。
    //【举例】
    //express="1^0|0|1"，desired=false
    //只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。 express="1"，desired=false
    //无组合则可以得到false，返回0

    public static int num1(String express, boolean desired) {
        if (express == null || express.length() == 0) {
            return 0;
        }
        char[] str = express.toCharArray();
        if (!isValid(str)) {
            return 0;
        }

        return f(str, desired, 0, str.length - 1);

    }


    public static boolean isValid(char[] express) {
        //如果不是奇数长度 直接返回
        if ((express.length & 1) == 0) {
            return false;
        }
        // 偶数长度位置 必须为 0 或者 1
        for (int i = 0; i < express.length; i+= 2) {
            if (express[i] != '0' && express[i] != '1') {
                return false;
            }
        }
        // 奇数长度位置 必须为 | ^ &
        for (int i = 1; i < express.length; i += 2) {
            if (express[i] != '|' && express[i] != '&' && express[i] != '^') {
                return false;
            }
        }
        return true;
    }


    // str[L..R] 返回期待为desired的方法数
    // 潜台词：L R 必须是偶数位置
    public static int f(char[] str, boolean desired, int L, int R) {

        if (L == R) {
            if (str[L] == '1') {
                return desired ? 1 : 0;
            } else { // '0'
                return desired ? 0 : 1;
            }
        }

        int res = 0;
        if (desired) {
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        break;
                    case '|':
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '^':
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        break;
                }
            }
        }else{
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '|':
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '^':
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        break;
                }
            }
        }
        return res;
    }


    public static int dp(String express, boolean desired) {
        if (express == null || express.length() == 0) {
            return 0;
        }

        char[] str = express.toCharArray();
        int N = str.length;

        //tMap[i][j] ==> L = i R = j 为 true
        int[][] tMap = new int[N][N];
        int[][] fMap = new int[N][N];


        for (int i = 0; i < N; i+=2) {
            tMap[i][i] = str[i] == '1' ? 1 : 0;
            fMap[i][i] = str[i] == '0' ? 1 : 0;
        }

        for (int row = N - 3; row >= 0; row -= 2) {
            for (int col = row + 2; col < N; col += 2) {
                for (int i = row + 1; i < col; i += 2) {
                    switch (str[i]) {
                        case '&':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '|':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '^':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            break;
                    }
                    switch (str[i]) {
                        case '&':
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            fMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            fMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '|':
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '^':
                            fMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                    }
                }
            }
        }
        return desired ? tMap[0][N -1] : fMap[0][N -1];
    }


    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
        System.out.println(num1(express, desired));
//        System.out.println(num2(express, desired));
        System.out.println(dp(express, desired));
//        System.out.println(dpLive(express, desired));
    }





}
