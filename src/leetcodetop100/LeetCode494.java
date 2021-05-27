package leetcodetop100;

public class LeetCode494 {


    public int findTargetSumWays(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((target + sum) % 2 == 1) {
            return 0;
        }

        int half = (target + sum ) >> 1;
        int[] dp = new int[half + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int s = half; s >= nums[i]; s--) {
                dp[s] += dp[s - nums[i]];
            }
        }
        return dp[half];
    }

}
