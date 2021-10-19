package algorithmlearning003.class01;

import java.util.Arrays;

public class Code01_CordCoverMaxPoint {

    //给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置  有序的数组
    //给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
    //绳子的边缘点碰到X轴上的点，也算盖住
    public static int maxPoint1(int[] arr, int L) {//O(N*logN)
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            //找到大于等于等于arr[i] - L 的最左位置 即 能盖住的点
            int nearestIndex = nearestIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearestIndex + 1);
        }
        return res;
    }

    /**
     * 找到大于等于该数的最左位置
     *
     * @param arr
     * @param R
     * @param value
     * @return
     */
    public static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                R = mid - 1;
                index = mid;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    //O(N)
    public static int maxPoint2(int[] arr, int L) {
        int left = 0;
        int R = 0;
        int N = arr.length;
        int res = 0;
        //双指针
        while (left < N) {
            //如果还在绳子长度内 右边界继续移动
            while (R < N && arr[R] - arr[left] <= L) {
                R++;
            }
            //移动第一个不满足的位置 结算 并 移动左边界
            res = Math.max(res, R - (left++));
        }
        return res;
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

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
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
