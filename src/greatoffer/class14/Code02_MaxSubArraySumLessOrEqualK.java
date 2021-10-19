package greatoffer.class14;

import java.util.HashSet;
import java.util.TreeSet;

public class Code02_MaxSubArraySumLessOrEqualK {


    // 请返回arr中，求个子数组的累加和，是<=K的，并且是最大的。
    // 返回这个最大的累加和
    public static int getMaxLessOrEqualK(int[] arr, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (set.ceiling(sum - k) != null) {
                res = Math.max(res, sum - set.ceiling(sum - k));
            }
            set.add(sum);
        }
        return res;
    }

}
