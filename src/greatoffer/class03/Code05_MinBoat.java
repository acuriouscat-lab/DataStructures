package greatoffer.class03;

import java.util.Arrays;

public class Code05_MinBoat {

    // 给定一个正数数组arr，代表若干人的体重
    // 再给定一个正数limit，表示所有船共同拥有的载重量
    // 每艘船最多坐两人，且不能超过载重
    // 想让所有的人同时过河，并且用最好的分配方法让船尽量少
    // 返回最少的船数

    public static int minBoat(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        Arrays.sort(arr);
        int lessR = -1;
        // 找到小于limit / 2 最右的位置
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return N;
        }
        int L = lessR;
        int R = lessR + 1;
        // 表示左边没有办法和右边结合的个数
        int noUsed = 0;
        while (L >= 0) {
            // 表示左边解决掉的个数
            int solved = 0;
            while (R < N && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1;
        // 左边结合了的个数
        int used = all - noUsed;
        // 右边没办法结合的，必须单独一艘船
        int moreUnsolved = (N - all) - used;
        // 结合了的（一艘船） + （左边没办法和右边结合的，那么只能喝左边结合）(两人一艘【向上取整】) + 右边没办法结合的（一人一艘船）
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }


}
