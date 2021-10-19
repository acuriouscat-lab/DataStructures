package greatoffer.class03;

public class Code03_Largest1BorderedSquare {

    //给定一个只有0和1组成的二维数组
    //
    //返回边框全是1的最大正方形面积

    public static int largest1BorderedSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix.length;
        int[][] down = new int[N][M];
        int[][] right = new int[N][M];

        setBorderMap(matrix, down, right);

        for (int size = Math.min(N, M); size >= 1; size--) {
            if (hasSizeOfBorder(matrix, down, right, size)) {
                return size * size;
            }
        }
        return 0;
    }

    /**
     *  生成预数组
     * @param m 原矩阵
     * @param down 下矩阵：(i,j) 表示 (i,j) 下方有多少个连续的 1
     * @param right 右矩阵：(i,j) 表示 (i,j) 右方方有多少个连续的 1
     */
    public static void setBorderMap(int[][] m, int[][] down, int[][] right) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        // 最后一列
        for (int i = r - 2; i != -1; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        // 最后一行
        for (int i = c - 2; i != -1; i--) {
            if (m[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        for (int i = r - 2; i != -1; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    // 枚举每一个左上角点，判断是否有存在为 size 大小的
    public static boolean hasSizeOfBorder(int[][] matrix, int[][] down, int[][] right, int size) {
        for (int i = 0; i < matrix.length - size + 1; i++) {
            for (int j = 0; j < matrix[0].length - size + 1; j++) {
                if (down[i][j] >= size &&
                        right[i][j] >= size &&
                        down[i][j + size - 1] >= size && right[i + size - 1][j] >= size) {
                    return true;
                }
            }
        }
        return false;
    }



}
