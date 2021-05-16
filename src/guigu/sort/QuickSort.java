package guigu.sort;

import java.util.Arrays;

/**
 *  快速排序：
 *              1.选定中心轴
 *              2.将比中心轴小的放在左边
 *              3.比中心轴大的放在右边
 *              4.再对左右子序列重复操作
 *
 *              最差为o(N^2)
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 0, -23, -567};
        quickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    private static void quickSort2(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int key = arr[left];
        if (l < r) {
            while (arr[r] > key && l < r) {
                r--;
            }
            if (l < r) {
                arr[l++] = arr[r];
            }
            while (arr[l] < key && l < r) {
                l++;
            }
            if (l < r) {
                arr[r--] = arr[l];
            }
            arr[l] = key;
            quickSort2(arr, left, l - 1);
            quickSort2(arr, l + 1, right);
        }

    }

    private static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int key = arr[l];
        while (l < r) {
            while (l < r && arr[r] > key) {
                r--;
            }
            if (l < r) {
                arr[l++] = arr[r];
            }
            while (l < r && arr[l] < key) {
                l++;
            }
            if (l < r) {
                arr[r--] = arr[l];
            }
            if (l>=r) {
                arr[l] = key;
            }
        }
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }



//        int mid = arr[(left+right)/2];
//        int key = arr[l];
//        int temp;

//        while (l < r) {
//            while (l < r && arr[r] >= key) {
//                r--;
//            }
//            if (l < r) {
//                arr[l++] = arr[r];
//            }
//            while(l<r && arr[l]<=arr[])
//        }

//        while (l < r) {
//            while (arr[l] < mid) {
//                l++;
//            }
//            while (arr[r] > mid) {
//                r--;
//            }
//            if (l == r) {
//                break;
//            }
//            temp = arr[l];
//            arr[l] = arr[r];
//            arr[r] = temp;
//            if (arr[l] == mid) {
//                r--;
//            }
//            if (arr[r] == mid) {
//                l++;
//            }
//        }
//        if (l == r) {
//            l++;
//            r--;
//        }
//        if (left < r) {
//            quickSort(arr, left, r);
//        }
//        if (right > l) {
//            quickSort(arr, l, right);
//        }
//    }

}
