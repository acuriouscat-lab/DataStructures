package greatoffer.class07;

/**
 * // 给定一个正数组成的数组，长度一定大于1，求数组中哪两个数与的结果最大
 */
public class Code01_MaxAndValue {

    // 暴力解
    public static int maxAndValue1(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.max(ans, arr[i] & arr[j]);
            }
        }
        return ans;
    }

    // .....
    // 现在来到i位，假设剩余的数字有M个，看看这一位是1的数，有几个
    // 如果有0个、或者1个
    // 说明不管怎么在M个数中选择，任何两个数&的结果在第i位上都不可能有1了
    // 答案在第i位上的状态一定是0，
    // 保留剩余的M个数，继续考察第i-1位
    // 如果有2个，
    // 说明答案就是这两个数(直接返回答案)，因为别的数在第i位都没有1，就这两个数有。
    // 如果有>2个，比如K个
    // 说明答案一定只用在这K个数中去选择某两个数，因为别的数在第i位都没有1，就这K个数有。
    // 答案在第i位上的状态一定是1，
    // 只把这K个数作为剩余的数，继续考察第i-1位，其他数都淘汰掉
    public static int maxAndValue2(int[] arr) {
        int M = arr.length;
        int ans = 0;
        for (int bit = 30; bit >= 0; bit--) {
            int i = 0;
            int temp = M;// 右边界
            while (i < M) {
                // 判断当前 bit 位 有多少个 1 不为 1 的话 交换到右边界右侧
                if (((arr[i] >> bit) & 1) == 0) {
                    swap(arr, i, --M);
                }else{
                    i++;
                }
            }
            if (M == 2) {
                return arr[0] & arr[1];
            }
            if (M < 2) {
                M = temp;
            }else{ // 大于 2 说明当前 bit 位，一定为 0
                ans |= (1 << bit);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static int[] randomArray(int size, int range) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * range) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int range = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * maxSize) + 2;
            int[] arr = randomArray(size, range);
            int ans1 = maxAndValue1(arr);
            int ans2 = maxAndValue2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }


}
