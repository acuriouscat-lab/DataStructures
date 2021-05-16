package algorithmlearning001.class02;

public class Code01_FibonacciProblem {
    //斐波那契数列
    public static int f1(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return f1(n - 1) + f1(n - 2);
    }

    public static int f2(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int res = 1;
        int pre = 1;
        int temp = 0 ;
        for (int i = 3; i <= n; i++) {
            temp = res;
            res = res + pre;
            pre = temp;
        }

        return res;
    }

    //根据递推式得到的行列式计算  //斐波那契数列  O(logN)
    public static int f3(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }
        //| F(N) , F(N-1) | = | F(2), F(1) |  *  base矩阵的N-2次方
        int[][] base = {{1, 1}, {1, 0}};

        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    private static int[][] matrixPower(int[][] base, int p) {
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }

        int[][] tmp = base;
        //根据二进制位上是1的时候进行运算 每判断一次tmp/进制 相乘
        for (; p != 0; p = p >> 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(tmp, tmp);
        }
        return res;
    }

    //矩阵的乘法
    private static int[][] multiMatrix(int[][] res, int[][] tmp) {
        int[][] ans = new int[res.length][tmp[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                for (int k = 0; k < tmp.length; k++) {
                    ans[i][j] += res[i][k] * tmp[k][j];
                }
            }
        }
        return ans;
    }


    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return s1(n - 1) + s1(n - 2);
    }

    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public static int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {
                { 1, 1, 0 },
                { 0, 0, 1 },
                { 1, 0, 0 } };
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");

        System.out.println(s1(n));
        System.out.println(s2(n));
        System.out.println(s3(n));
        System.out.println("===");

        System.out.println(c1(n));
        System.out.println(c2(n));
        System.out.println(c3(n));
        System.out.println("===");

    }


}
