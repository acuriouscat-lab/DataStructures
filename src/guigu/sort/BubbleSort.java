package guigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 冒泡排序基本思想：
 *          从前往后，依次比较相连元素的值，若发现后面的值比前面的值小，则交换位置
 *  优化：     若一趟下来没有发生过交换，则说明该序列有序
 *  时间复杂度为O(n^2)
 */
public class BubbleSort {

    public static void main(String[] args) {
        //int arr[] = {3, 9, -1, 10, 20, 5};
        int arr[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
           arr[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
//        System.out.println("======================排序前======================");
//        System.out.println(Arrays.toString(arr));
//        long x = System.currentTimeMillis();
//        System.out.println("======================排序后======================");
        bubbleSort2(arr);
        Date date2 = new Date();
        String format1 = simpleDateFormat.format(date2);
        System.out.println(format1);
//        long x1 = System.currentTimeMillis();
//        System.out.println(x1-x);
//        System.out.println(Arrays.toString(arr));

    }

    private static void bubbleSort(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
           if (!flag) {
               break;
            }else{
               flag = false;
           }
        }
    }

    private static void bubbleSort2(int[] arr) {
        //判断是否存在一趟下来没有发生交换的标记 默认为false
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i + 1; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }

                //判断是否没有发生过交换
                if (!flag) {
                    return;
                } else {
                    flag = false;
                }
            }
        }
    }

}
