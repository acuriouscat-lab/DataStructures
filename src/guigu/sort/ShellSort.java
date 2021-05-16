package guigu.sort;

import java.util.Arrays;

/**
 *    希尔排序： 将序列按照增量进行分组，对每组进行直接插入排序算法
 */
public class ShellSort {
    public static void main(String[] args) {
        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        int arr[] = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(date1);
//        System.out.println(format);
        shellSort2(arr);
         System.out.println(Arrays.toString(arr));
//        Date date2 = new Date();
//        String format1 = simpleDateFormat.format(date2);
//        System.out.println(format1);

    }
    //交换法
    private static void shellSort(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int j = gap; j < arr.length; j++) {
                for (int k = j - gap; k >= 0; k -= gap) {
                    if (arr[k] > arr[k + gap]) {
                        temp = arr[k];
                        arr[k] = arr[k + gap];
                        arr[k + gap] = temp;
                    }
                }
            }
        }
    }


    //交换法
    private static void shellSort11(int[] arr) {
        int temp;
        //进行分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //对每组进行简单插入排序
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //移位法
    private static void shellSort2(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) { //按照增量进行分组 每组进行插入排序 分组不断减小到1
            for (int i = gap; i < arr.length; i++) {  //每组进行插入排序
                int j = i;
                temp = arr[i];  //要进行插入的值
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                }
                arr[j] = temp;
            }
        }
    }

//    //移位法
//    private static void shellSort21(int[] arr) {
//        int temp;
//        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
//            for (int i = gap; i < arr.length; i++) {
//                int insertIndex = i;
//                temp = arr[i];
//                if (arr[insertIndex] < arr[insertIndex - gap]) {
//                    while (insertIndex - gap >= 0 && temp < arr[insertIndex - gap]) {
//                        arr[insertIndex] = arr[insertIndex - gap];
//                        insertIndex -= gap;
//                    }
//                }
//                arr[insertIndex] = temp;
//            }
//        }
//    }
}
