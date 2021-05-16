package guigu.tree;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};

        heapSort(arr);
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序");

        // adjustHeap(arr, 1, arr.length);
        for (int i = arr.length / 2-1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //将堆顶元素与末尾元素交换，将最大元素放到数组末端
        //重新调整结构时期满足堆定义  然后继续交换堆顶元素与末尾元素  反复执行调整+交换步骤直到序列有序
        for (int j = arr.length - 1; j >= 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));

    }

    //将一个数组，调整为一个大顶堆

    /**
     * 将以i对应的非叶子节点调整为一个大顶堆
     *
     * @param arr    要调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //保存当前i得到数值
        int temp = arr[i];
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;    //指向右子节点
            }

            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }

}
