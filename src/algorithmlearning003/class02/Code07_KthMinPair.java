package algorithmlearning003.class02;

import java.util.Arrays;

public class Code07_KthMinPair {
    //长度为N的数组arr，一定可以组成N^2个数值对。
    //例如arr = [3,1,2]，
    //数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，
    //也就是任意两个数都有数值对，而且自己和自己也算数值对。
    //数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。所以上面的数值对排序的结果为：
    //(1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
    //
    //给定一个数组arr，和整数k，返回第k小的数值对。

    // O(N*logN)的复杂度，你肯定过了
    public static int[] kthMinPair2(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }
        // O(N*logN)
        Arrays.sort(arr);
        // 第K小的数值对，第一维数字，是什么   是arr中
        int fristNum = arr[(k - 1) / N];
        int lessFristNumSize = 0;// 数出比fristNum小的数有几个
        int fristNumSize = 0; // 数出==fristNum的数有几个
        // <= fristNum
        for (int i = 0; i < N && arr[i] <= fristNum; i++) {
            if (arr[i] < fristNum) {
                lessFristNumSize++;
            } else {
                fristNumSize++;
            }
        }
        int rest = k - (lessFristNumSize * N);
        return new int[] { fristNum, arr[(rest - 1) / fristNumSize] };
    }



    // O(N)的复杂度，你肯定蒙了
    public static int[] kthMinPair3(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }
        // 在无序数组中，找到第K小的数，返回值
        // 第K小，以1作为开始
        int fristNum = getMinKth(arr, (k - 1) / N);
        int lessFristNumSize = 0;
        int fristNumSize = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] < fristNum) {
                lessFristNumSize++;
            }
            if (arr[i] == fristNum) {
                fristNumSize++;
            }
        }
        int rest = k - (lessFristNumSize * N);
        return new int[] { fristNum, getMinKth(arr, (rest - 1) / fristNumSize) };
    }

    // 改写快排，时间复杂度O(N)
    // 在无序数组arr中，找到，如果排序的话，arr[index]的数是什么？
    public static int getMinKth(int[] arr, int index) {
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (index < range[0]) {
                R = range[0] - 1;
            } else if (index > range[1]) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[L];
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[] { less + 1, more - 1 };
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
