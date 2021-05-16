package guigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *      插入排序基本思想：将n个待排序的元素看成为一个有序序列和无序序列，每次从无序序列中取出一个元素
 *                      与有序序列中的元素进行比较，插入到适当的位置
 *     时间复杂度为O(n^2)
 */
public class insertSort {

    public static void main(String[] args) {
//        int arr[] = {101, 34, 119, -1, 90, 23};
//        System.out.println(Arrays.toString(arr));
//        System.out.println("======================");
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        insertSort2(arr);
        Date date1 = new Date();
        String format1 = simpleDateFormat.format(date1);
        System.out.println(format1);

    }

//    private static void insertSort(int[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int insertIndex = i -1;
//            int insertVal = arr[i];
//            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
//                arr[insertIndex+1] = arr[insertIndex];
//                insertIndex--;
//            }
//            if (insertIndex + 1 != i) {
//                arr[++insertIndex] = insertVal;
//            }
//        }
//    }







    private static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertIndex = i - 1;    //要插入位置的索引
            int insertValue = arr[i];   //要进行插入的值
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {//插入的索引大于0并且插入的值小于当前插入索引所对应的值
                arr[insertIndex+ 1] = arr[insertIndex ];    //让数值向后移动一步
                insertIndex--;  //向前搜索
            }
            if (insertIndex + 1 != i) {
                arr[++insertIndex] = insertValue;
            }
        }
    }
}
