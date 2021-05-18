package greatoffer.class02;

public class Code06_MinLengthForSort {

    //给定一个数组arr，只能对arr中的一个子数组排序，
    //但是想让arr整体都有序
    //返回满足这一设定的子数组中，最短的是多长

    public static int getMinLength(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int leftMax = Integer.MIN_VALUE;
        int leftIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if(leftMax <= arr[i]){
                leftMax = arr[i];
            }else{
                leftIndex = i;
            }
        }
        if (leftIndex == -1) {
            return 0;
        }
        int rightMin = Integer.MAX_VALUE;
        int rightIndex = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (rightMin >= arr[i]) {
                rightMin = arr[i];
            }else{
                rightIndex = i;
            }
        }
        return leftIndex - rightIndex + 1;
    }

    public static int getMinLength1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i != -1; i--) {
            if (arr[i] > min) {
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
        System.out.println(getMinLength(arr));
        System.out.println(getMinLength1(arr));
    }


}
