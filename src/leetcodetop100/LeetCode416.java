package leetcodetop100;

public class LeetCode416 {

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((sum & 1) != 0) {
            return false;
        }
        int half = sum >> 1;
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = half; j >= nums[i]; j--) {
                dp[j] |= dp[j - nums[i]];
            }
            if (dp[half]) {
                return true;
            }
        }
        return false;
    }

}
