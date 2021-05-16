package algorithmlearning002.class02;

import java.util.HashMap;

public class Code02_LongestSumSubArrayLength {

    //给定一个整数组成的无序数组arr，值可能正、可能负、可能0
    //给定一个整数值K
    //找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
    //返回其长度
    //必须以每个i结尾能够得到的最长的长度
    public static int maxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;
        int sum =0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        //map -- key 当前累加和   value 当前的下标
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            if (map.containsKey(sum - K)) {//如果之前有一段累加和 == sum - k  说明=sum-k位置到i位置满足
                len = Math.max(len,i - map.get(sum - K));
            }
            if (!map.containsKey(sum)) {//因为是最长的范围  即使后面存在一样的也不添加了
                map.put(sum, i);
            }
        }
        return len;
    }

    // for test
    public static int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generateRandomArray(int size, int value) {
        int[] ans = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;

        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int K = (int) (Math.random() * value) - (int) (Math.random() * value);
            int ans1 = maxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");

    }
}
