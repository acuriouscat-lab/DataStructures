package guigu.search;

import java.util.Arrays;

public class FibonachSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234,1236};
        System.out.println("下标为：" + fibSearch(arr, 1236));
    }

    //生成一个fIbo数组
    public static int[] fib(){
        int fib[] = new int[maxSize];
        fib[0] = fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    private static int fibSearch(int[] arr,  int value) {
        int[] fib = fib();
        int k = 0;
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        while (arr.length >= fib[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(arr, fib[k]-1);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low < high) {
            mid = fib[k - 1] + 1;
            if (value < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (value > temp[mid]) {
                low = mid + 1;
                k -= 2;
            }else{
                if (mid <= high) {
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;
    }
}
