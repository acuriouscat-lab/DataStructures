package greatoffer.class18;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-19 16:38
 */
public class Code01_HanoiProblem {

    /*
        给定一个数组arr，长度为N，arr中的值只有1，2，3三种
        arr[i] == 1，代表汉诺塔问题中，从上往下第i个圆盘目前在左
        arr[i] == 2，代表汉诺塔问题中，从上往下第i个圆盘目前在中
        arr[i] == 3，代表汉诺塔问题中，从上往下第i个圆盘目前在右
        那么arr整体就代表汉诺塔游戏过程中的一个状况
        如果这个状况不是汉诺塔最优解运动过程中的状况，返回-1
        如果这个状况是汉诺塔最优解运动过程中的状况，返回它是第几个状况
     */
    public static int step1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;

        }
        return process(arr, arr.length - 1, 1, 2, 3);
    }


    //  0...index 块 的塔要从 from -> to 中间需要借用 other , 需要几步
    public static int process(int[] arr, int index, int from, int other, int to) {
        if (index == -1) {
            return 0;
        }
        if (arr[index] != from && arr[index] != to) {
            return -1;
        }
        // 函罗塔的最优方案 --- I 层
        // 1. 先将 i - 1 层的转移到 other
        // 2. 再将 第 i 层转移到 to
        // 3. 将 i - 1层从  other -> to
        if (arr[index] == from) { // 第一阶段还没完成
            return process(arr, index - 1, from, to, other);
        }else{ // 第一、二阶段完成
            int rest = process(arr, index - 1, other, from, to);
            if (rest == -1) {
                return -1;
            }// i 层函罗塔的最优为 (2 ^ i) - 1
            return (1 << index) + rest;
        }
    }

    public static int step2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int tmp = 0;
        while (i >= 0) {
            if (arr[i] != from && arr[i] != to) {
                return -1;
            }
            if (arr[i] == to) {
                res += 1 << i;
                tmp = from;
                from = mid;
            } else {
                tmp = to;
                to = mid;
            }
            mid = tmp;
            i--;
        }
        return res;
    }

    public static int kth(int[] arr) {
        int N = arr.length;
        return step(arr, N - 1, 1, 3, 2);
    }

    // 0...index这些圆盘，arr[0..index] index+1层塔
    // 在哪？from 去哪？to 另一个是啥？other
    // arr[0..index]这些状态，是index+1层汉诺塔问题的，最优解第几步
    public static int step(int[] arr, int index, int from, int to, int other) {
        if (index == -1) {
            return 0;
        }
        if (arr[index] == other) {
            return -1;
        }
        // arr[index] == from arr[index] == to;
        if (arr[index] == from) {
            return step(arr, index - 1, from, other, to);
        } else {
            int p1 = (1 << index) - 1;
            int p2 = 1;
            int p3 = step(arr, index - 1, other, to, from);
            if (p3 == -1) {
                return -1;
            }
            return p1 + p2 + p3;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 3, 2, 1 };
        System.out.println(step1(arr));
        System.out.println(step2(arr));
        System.out.println(kth(arr));
    }


}
