package test;

import java.util.Arrays;

public class LeetCode152 {


    public static void main(String[] args) {
        maxProduct(new int[]{2, 3, -2, 4});
    }

    public static int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[1];
        for(int i = 1; i < nums.length; i++){
            dp[i][0] = Math.max(nums[i],Math.max(nums[i] * dp[i - 1][0],nums[i] * dp[i - 1][1]));
            dp[i][1] = Math.min(nums[i],Math.min(nums[i] * dp[i - 1][0],nums[i] * dp[i - 1][1]));
            ans = Math.max(ans,dp[i][0]);
        }
        for(int[] ints : dp){
            System.out.println(Arrays.toString(ints));
        }
        return ans;
    }



}
