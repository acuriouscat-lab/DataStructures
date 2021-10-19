package leetcodetop100;

import java.util.Arrays;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-03 18:51
 */
public class LeetCode59 {


    public static void main(String[] args) {
        int[][] ints = generateMatrix(2);
        for (int[] ints1 : ints) {
            System.out.println(Arrays.toString(ints1));
        }
    }

    static int cur = 1;

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int sr = 0;
        int sc = 0;
        int er = n - 1;
        int ec = n - 1;
        while(sr <= er){
            paint(matrix,sr++,sc++,er--,ec--);

        }
        return matrix;
    }

    public static void paint(int[][] matrix,int row,int col,int erow,int ecol){
        int i = col;
        for(; i <= ecol; i++){
            matrix[row][i] = cur++;
        }
        int j = row + 1;
        for(; j <= erow; j++){
            matrix[j][ecol] = cur++;
        }
        i = ecol - 1;
        for(; i >= col; i--){
            matrix[erow][i] = cur++;
        }
        j = erow - 1;
        for(; j > row; j--){
            matrix[j][col] = cur++;
        }
    }

}
