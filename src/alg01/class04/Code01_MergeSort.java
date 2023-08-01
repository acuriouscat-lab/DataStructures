package alg01.class04;

import java.util.Arrays;

public class Code01_MergeSort {
    //1）整体是递归，左边排好序+右边排好序+merge让整体有序
    //2）让其整体有序的过程里用了排外序方法
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] helpArr = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= right) {
            helpArr[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            helpArr[index++] = arr[p1++];
        }
        while (p2 <= right) {
            helpArr[index++] = arr[p2++];
        }
        System.arraycopy(helpArr, 0, arr, left, helpArr.length);
    }

    // 迭代版本实现归并排序
    public static void mergeSort2(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        // 每 mergeSize 个为一组进行merge
        int mergeSize = 1;
        int N = arr.length;
        while(mergeSize < N){
            // 当前组的最左的坐标
            int left = 0;
            while(left < N){
                if(left >= N - mergeSize){
                    break;
                }
                int mid = left + mergeSize - 1;
                int right = Math.min(mid + mergeSize, N - 1);
                merge(arr, left, mid, right);
                left = right + 1;
            }
            mergeSize <<= 1;
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
//            Arrays.sort(arr2);

            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
