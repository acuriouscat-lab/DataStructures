package algorithmlearning002.class01;

public class Code06_printMatrixZigZag {
    public static void printMatrixZigZag(int[][] matrix) {
        int uR = 0;
        int uC = 0;
        int lR = 0;
        int lC = 0;
        int endR = matrix.length-1;
        int endC = matrix[0].length-1;
        boolean fromUp = false;
        while (uR != endR +1) {
            printLevel(matrix, uR, uC, lR, lC, fromUp);
            uR = uC == endC ? uR +1: uR;
            uC = uC == endC ? uC : uC +1;
            lC = lR == endR ? lC +1: lC;
            lR = lR == endR ? lR : lR +1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    public static void printLevel(int[][] m, int uR, int uC, int lR, int lC, boolean f) {
        if(f){
            while (uR <= lR) {
                System.out.print(m[uR++][uC--] + " ");
            }
        }else{
            while (lR >= uR) {
                System.out.print(m[lR--][lC++]+" ");
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}
