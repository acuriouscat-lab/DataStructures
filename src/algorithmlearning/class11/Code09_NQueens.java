package algorithmlearning.class11;

public class Code09_NQueens {

    public static int num1(int n) {
        if (n < 0) {
            return 0;
        }

        int[] record = new int[n];
        return process(record, 0, n);
    }

    private static int process(int[] record, int row, int n) {
        if(row == n) return 1;

        int res = 0;
        for (int col = 0; col < n; col++) {
            if (isValid(record, row, col)) {
                record[row] = col;
                res += process(record, row + 1, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int row, int col) {
        // i row record[i] col
        for (int i = 0; i < row; i++) {
                if (col == record[i] || Math.abs(i - row) == Math.abs(record[i] - col)) {
                    return false;
                }
        }
        return true;
    }

    //位运算   只是加速常数时间
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }

        //代表右边有几个1
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }
    // limit 划定了问题的规模 -> 固定
    // colLim 列的限制，1的位置不能放皇后，0的位置可以
    // leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
    // rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));//还可以哪些位置上可以选
        int mostRightOnde = 0;//最左侧的1
        int res = 0;
        while (pos != 0) {//如果还有的选 pos != 0
            mostRightOnde = pos & (~pos + 1);//获得pos最右侧的1  000000001
            pos = pos ^ mostRightOnde;//选择最右侧的位置
            res += process2(limit, colLim | mostRightOnde,
                    (leftDiaLim | mostRightOnde) << 1, (rightDiaLim | mostRightOnde) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 15;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
