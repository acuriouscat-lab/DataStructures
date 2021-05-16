package guigu.avltree;

import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] ints = {3, 4, 5, 6};
        System.out.println(fourSum(ints,18));
    }

    public static ArrayList<ArrayList<Integer>> fourSum(int[] nums, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len - 2;) {
                int l = j + 1;
                int r = len - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[l++], nums[r--])));
                        while (l < r && nums[l - 1] == nums[l]) {
                            l++;
                        }
                        while (l < r && nums[r + 1] == nums[r]) {
                            r--;
                        }
                    } else if (sum > target) {
                        r--;
                    } else {
                        l++;
                    }
                }
                j++;
                while (j < len - 2 && nums[j] == nums[j - 1]) {
                    j++;
                }
            }
        }
        return ans;
    }
}
