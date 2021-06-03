package algorithmlearning003.class06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Code05_Split4Parts {


    /*
    给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要。
    例如:
    arr=[3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2] 返回true
    三个切割点下标为2, 5, 7. 切出的四个子数组为[3,2], [1,4], [5], [1,2,2]，
    累加和都是5
     */

    public static boolean canSplits2(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        // map 记录当前位置之前的累加和（不包含当前位置)
        Map<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        // 第一刀左侧的位置
        int lSum = arr[0];
        // lSum  |  lSum |  lSum | lSum
        //       l      s2     s3
        for (int l = 1; l < arr.length - 5; l++) { // l 是第一刀的位置
            int nextSum = 2 * lSum + arr[l];
            if(map.containsKey(nextSum)){
                int s2 = map.get(nextSum); // s2 为第二道的位置
                nextSum += lSum + arr[s2];
                if(map.containsKey(nextSum)){
                    int s3 = map.get(nextSum); //s3 为第三刀的位置
                    if(nextSum + arr[s3] + lSum == sum){
                        return true;
                    }
                }
            }
            lSum += arr[l];
        }

        return false;


    }

    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        HashSet<String> set = new HashSet<String>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int leftSum = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            set.add(String.valueOf(leftSum) + "_" + String.valueOf(sum - leftSum - arr[i]));
            leftSum += arr[i];
        }
        int l = 1;//第一刀的位置
        int lsum = arr[0];// 第一刀左边的累加和
        int r = arr.length - 2;//第三刀的位置
        int rsum = arr[arr.length - 1];//第三刀右边的累加和
        while (l < r - 3) {
            if (lsum == rsum) { //如果两个相等，那么去找第二刀是否存在
                String lkey = String.valueOf(lsum * 2 + arr[l]);
                String rkey = String.valueOf(rsum * 2 + arr[r]);
                if (set.contains(lkey + "_" + rkey)) {
                    return true;
                }
                lsum += arr[l++];
            } else if (lsum < rsum) {
                lsum += arr[l++];
            } else {
                rsum += arr[r--];
            }
        }
        return false;
    }

    public static int[] generateRondomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 7];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 10) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 3000000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRondomArray();
            if (canSplits1(arr) ^ canSplits2(arr)) {
                System.out.println("Error");
            }
        }
    }






}
