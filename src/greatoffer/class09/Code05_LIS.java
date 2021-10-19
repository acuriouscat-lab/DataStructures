package greatoffer.class09;

public class Code05_LIS {


    // 本题测试链接 : https://leetcode.com/problems/longest-increasing-subsequence
    public static int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] end = new int[N];
        // end[i] 表示以 i + 1 长度的最长递增子序列的最小结尾为 end[i]
        end[0] = arr[0];
        int l = 0;
        int r = 0;
        int m = 0;
        // 有效区域右边界
        int right = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while(l <= r){
                m = (l + r) >> 1;
                if (end[m] < arr[i]) {
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            end[l] = arr[i];
        }
        return right + 1;
    }


}
