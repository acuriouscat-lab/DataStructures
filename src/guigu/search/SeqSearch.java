package guigu.search;

/**
 * 线性查找
 */
public class SeqSearch {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4,};
        int i = seqSearch(arr, 3);
        System.out.println("下标为：" + i);

    }

    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
