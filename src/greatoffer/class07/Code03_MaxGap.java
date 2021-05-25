package greatoffer.class07;

import java.util.Arrays;

public class Code03_MaxGap {

    //给定一个数组arr，
    //返回如果排序之后，相邻两数的最大差值
    //要求：时间复杂度O(N)

    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        if (min == max) {
            return 0;
        }
        boolean[] bucket = new boolean[len + 1];
        int[] maxBucket = new int[len + 1];
        int[] minBucket = new int[len + 1];
        for (int i = 0; i < len; i++) {
            int bucketId = getBucketId(arr[i], len , min, max);
            maxBucket[bucketId] = bucket[bucketId] ? Math.max(arr[i], maxBucket[bucketId]) : arr[i];
            minBucket[bucketId] = bucket[bucketId] ? Math.min(arr[i], minBucket[bucketId]) : arr[i];
            bucket[bucketId] = true;
        }
        int ans = 0;
        int i = 1;
        int lastMax = maxBucket[0];
        for (; i <= len; i++) {
            if(bucket[i]){
                ans = Math.max(ans,minBucket[i] - lastMax);
                lastMax = maxBucket[i];
            }
        }
        return ans;
    }

    public static int getBucketId(int val, int bucketLen, int min, int max) {
        return ((val - min) * bucketLen / (max - min));
    }


//    // 如果当前的数是num，整个范围是min~max，分成了len + 1份
//    // 返回num该进第几号桶
//    public static int getBucketId(long num, long len, long min, long max) {
//        return (int) ((num - min) * len / (max - min));
//    }




    // for test
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
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
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }




}
