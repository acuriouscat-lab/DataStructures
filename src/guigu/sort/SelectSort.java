package guigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 选择排序基本思想：从指定的规则中挑选出某一元素，再依照规定交换位置
*      首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
*       再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
*           重复第二步，直到所有元素均排序完毕。
 *  时间复杂度为O(n^2)
 */
public class SelectSort {
    public static void main(String[] args) {
        //int arr[] = {101, 34, 119, -1, 90, 23};
        int arr[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        //System.out.println("=======排序前=======");
        //System.out.println(Arrays.toString(arr));
        selectSort2(arr);
        Date date2 = new Date();
        String format1 = simpleDateFormat.format(date2);
        System.out.println(format1);
        //System.out.println("=======排序后=======");
       // System.out.println(Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    private static void selectSort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //寻找最小的值
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
                //如果最小值得index不是i则 说明有比arr[i]小的值
                if (minIndex != i) {
                    arr[minIndex] = arr[i];
                    arr[i] = min;
                }
            }
        }

    }
}
