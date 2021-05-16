package algorithmlearning001.class02;

public class Code02_ZeroLeftOneStringNumber {

    //给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
    //如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标
    //返回有多少达标的字符串
    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }

        return process(1, n);
    }

    private static int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }

        return process(i + 1, n) + process(i + 2, n);
    }

    public static int getNum2(int n) {
        if (n < 1) {
            return 0;
       }
        if (n == 1) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int temp = 0;
        for (int i = 2; i <= n; i++) {
            temp = res;
            res += pre;
            pre = temp;
        }

        return res;
    }


    public static int getNum3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n ==2) {
            return n;
        }

        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);

        return 2 * res[0][0] + res[1][0];
    }

    private static int[][] matrixPower(int[][] base, int i) {
        int[][] res = new int[base.length][base[0].length];
        for (int j = 0; j < base.length; j++) {
            res[j][j] = 1;
        }

        int[][] temp = base;
        for (; i != 0; i = i >> 1) {
            if ((i & 1) != 0) {
                res = multiMatrix(res, temp);
            }
            temp = multiMatrix(temp, temp);
        }
        return res;
    }

    private static int[][] multiMatrix(int[][] res, int[][] temp) {
        int[][] ans = new int[res.length][temp[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                for (int k = 0; k < temp.length; k++) {
                    ans[i][j] += res[i][k] * temp[k][j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i != 20; i++) {
            System.out.println(getNum1(i));
            System.out.println(getNum2(i));
            System.out.println(getNum3(i));
            System.out.println("===================");
        }

    }
}
