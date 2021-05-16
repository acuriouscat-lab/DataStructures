package guigu.tenalgorithm;

public class dynamic {

    public static void main(String[] args) {
        int[] w = {1, 4, 3};    //物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4;
        int n =val.length;

        //创建二维数组
        //v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品的情况，定义一个二位数组
        int[][] path = new int[n + 1][m + 1];

        //初始化
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //根据前面的公式来动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j]=v[i-1][j];
                }else{
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录放入的情况 使用if-else
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

        //输出path
        int i = v.length - 1;//行的最大索引
        int j = v[0].length - 1;//列的最大索引
        //因为是看最优的方案 所以是倒叙进行查找
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品被放入了背包\n", i);
                j-=w[i-1]; //因为已经放入了w[i-1]的重量了
            }
            i--; //去上一行
        }

    }
}
