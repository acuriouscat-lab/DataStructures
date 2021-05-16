package algorithmlearning003.class02;

public class Code01_FindNumInSortedMatrix {

    //在行也有序、列也有序的二维数组中，找num，找到返回true，否则false
    public static boolean findNum(int[][] m, int target) {
        if(m == null || m.length == 0){
            return false;
        }
        int i = 0;
        int j = m[0].length - 1;
        while (i < m.length && j > -1) {
            if (m[i][j] > target) {
                j--;
            } else if (m[i][j] < target) {
                i++;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = 234;
        System.out.println(findNum(matrix, K));
    }




}
