package greatoffer.class16;

/**
 * @author Administrator
 * @Description https://leetcode-cn.com/problems/patching-array/
 * @create 2021-07-13 21:46
 */
public class Code03_MinPatches {

    public int minPatches(int[] nums, int n) {
        int patch = 0;
        int range = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 > range) {
                range += range + 1;
                patch++;
                if (range >= n) {
                    return patch;
                }
            }
            range += nums[i];
            if (range >= n) {
                return patch;
            }

        }
        while (range < n) {
            range += range + 1;
            patch++;
        }
        return patch;
    }



}
