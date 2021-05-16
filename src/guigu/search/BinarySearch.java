package guigu.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000,1000, 1234};
        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 11111);
        System.out.println("下标为：" + integers);

    }

    private static int binarySearch(int[] arr, int left, int right, int value) {
        int mid = (left + right) / 2;
        if (left > right) {
            return -1;
        }
        if (arr[mid] > value) {
            return binarySearch(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {
            return binarySearch(arr, mid + 1, right, value);
        } else {
            return mid;
        }
    }


    //如果存在多个值
    private static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int findValue = arr[mid];

        if (findValue > value) {
            return binarySearch2(arr, left, mid - 1, value);
        } else if (findValue < value) {
            return binarySearch2(arr, mid + 1, right, value);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp--;
            }

            list.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp >= arr.length || arr[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp++;
            }

            return list;
        }
    }
}
