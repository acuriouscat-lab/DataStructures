package greatoffer.class04;

public class Code02_SubArrayMaxSum {


    //返回一个数组中，子数组最大累加和
    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static int maxSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = arr[0];
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre = Math.max(pre + arr[i], arr[i]);
            max = Math.max(max, pre);
        }
        return max;
    }
}
