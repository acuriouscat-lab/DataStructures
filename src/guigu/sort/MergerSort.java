package guigu.sort;

import java.util.Arrays;

/**
 * 归并排序
 *          分治策略
 *          归并排序（Merge guigu.sort）是建立在归并操作上的一种有效、稳定的排序算法，
 *          该算法是采用分治法(Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；
 *          即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。
 *
 *          o(N*logN)
 */
public class MergerSort {

    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSOrt(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

    }
    //分+合的方法
    public static void mergeSOrt(int[] arr, int left, int right, int[] temp) {
        if (left<right) {
            int mid = (left + right) / 2;
            mergeSOrt(arr, left, mid, temp);
            mergeSOrt(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }
    /**
     * 合并的方法
     * @param arr   排序的原始数组
     * @param left  左序列的初始索引
     * @param mid   右序列的初始索引
     * @param right 右边的索引
     * @param temp  临时的数组
     */
    public static void merge(int[] arr, int left, int mid,int right, int[] temp) {
        int i = left;       //左边序列的初始索引
        int j = mid + 1;    //右边序列的初始索引
        int t = 0;  //指向temp数组的当前索引
        //把左右有序序列合并到temp数组中
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            }else{
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        //如果一边不为空的话则全部复制
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        //将temp数组中的数据copy到arr数组中
        //并不是每一次都全部复制
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
