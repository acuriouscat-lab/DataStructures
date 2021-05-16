package algorithmlearning.class05;

import java.util.Arrays;

public class Code_RadixSort {

    //only for non-negative number
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return ;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    private static void radixSort(int[] arr, int L, int R, int maxbits) {
        final int radix = 10;
        int i, j = 0;
        int[] help = new int[R - L + 1];
        for (int k = 1; k <= maxbits; k++) {
            int[] count = new int[radix];
            //统计在第k位上的数 在count中
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], k);
                count[j]++;
            }
            //将count进行一些变化
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            //根据count里的值，从后往前遍历 将数放到help中
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i],k);
                help[count[j]-1] = arr[i];
                count[j]--;
            }
            //再将help的值放入到arr
            for (i = L, j = 0; i <= R; i++,j++) {
                arr[i] = help[j];
            }
        }
    }
    //数值x在第k位上的值
    private static int getDigit(int x, int k) {
        return (x / (int) (Math.pow(10, k - 1))) % 10;
    }

    //返回数组里最大值的位数
    private static int maxbits(int[] arr) {
        int res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            res = Math.max(res, arr[i]);
        }
        int count = 0;
        while (res != 0) {
            count++;
            res /= 10;
        }
        return count;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }
}
