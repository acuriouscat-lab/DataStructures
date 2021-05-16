package greatoffer.class01;

import java.util.Arrays;

public class Code01_CordCoverMaxPoint {

    // problem:
    //给定一个有序数组arr，代表坐落在X轴上的点
    //给定一个正数K，代表绳子的长度
    //返回绳子最多压中几个点？
    //即使绳子边缘处盖住点也算盖住

    // n * log(n)
    public static int  maxPoint1(int[] arr, int K){
        int ans = 1;
        // 每一次按照当前位置作为右边界，看看往左边能够遮住多少个
        for(int i = 0; i < arr.length; i++){
            int nearest = nearestIndex(arr, i, arr[i] - K);
            ans = Math.max(ans, i - nearest + 1);
        }
        return ans;
    }

    // 在 0...R 上找到大于等于 val,最左边的位置
    public static int nearestIndex(int[] arr,int R,int val){
        int left = 0;
        int right = R;
        int m = 0;
        int ans = R;
        while (left <= right) {
            m = (left + right) >> 1;
            if(arr[m] < val){
                left = m + 1;
            }else{
                ans = m;
                right = m - 1;
            }
        }
        return ans;
    }

    // O(n)
    public static int maxPoint2(int[] arr,int k){
        int left = 0;
        int right = 0;
        int ans = 1;
        // 双指针 只要 满足能过够覆盖范围，right一直往右边扩
        while (right < arr.length) {
            while (right < arr.length && arr[right] - arr[left] <= k) {
                right++;
            }
            ans = Math.max(ans,right - (left++));
        }
        return ans;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }


    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }

    }



}
