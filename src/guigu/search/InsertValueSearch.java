package guigu.search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000,1000, 1234};
        int i = insertValueSearch(arr, 0, arr.length - 1, 11111);
        System.out.println("下标为：" + i);
    }

    private static int insertValueSearch(int[] arr, int left, int right, int value) {
        System.out.println(1);
        //后面两个条件为防止value不存在时，mid过大或者过小造成数组越界异常
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (value - arr[left]) / (arr[right] - arr[left]) * (right - left);
        if (value < arr[mid]) {
            return insertValueSearch(arr, left, mid - 1, value);
        } else if (value > arr[mid]) {
            return insertValueSearch(arr, mid + 1, right, value);
        }else{
            return mid;
        }
    }
}
