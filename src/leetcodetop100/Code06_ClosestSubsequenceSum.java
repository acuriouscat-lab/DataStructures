package leetcodetop100;

import java.util.Arrays;

public class Code06_ClosestSubsequenceSum {

    static int[] l = new int[1 << 20];
    static int[] r = new int[1 << 20];

    public int minAbsDifference(int[] nums, int goal) {
        int le = process(nums, 0, nums.length >> 1, 0, 0, l);
        int re = process(nums, nums.length >> 1, nums.length, 0, 0, r);
        Arrays.sort(l, 0, le);
        Arrays.sort(r, 0, re--);
        int ans = Math.abs(goal);
        for (int i = 0; i < le; i++) {
            int rest = goal - l[i];
            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
                re--;
            }
            ans = Math.min(ans, Math.abs(rest - r[re]));
        }
        return ans;
    }

    public int process(int[] nums, int index, int end,int fill, int sum, int[] arr) {
        if (index == end) {
            arr[fill++] = sum;
        } else {
            fill = process(nums, index + 1, end,fill, sum, arr);
            fill = process(nums, index + 1, end,fill, sum + nums[index], arr);
        }
        return fill;
    }


}
