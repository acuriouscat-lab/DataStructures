package algorithmlearning001.class01;

import java.util.LinkedList;

public class Code02_AllLessNUmSubArray {
    //给定一个整型数组arr，和一个整数num
    //某个arr中的子数组sub，如果想达标，必须满足：
    //sub中最大值 – sub中最小值 <= num，
    //返回arr中达标子数组的数量

    //研究的问题和范围存在单调性：
    //大的可以 小的一定可以
    //小的不行 大的一定不行
    // ==》   同时维护窗口内的最大值和最小值
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //存储的是元素的下标
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int res = 0;
        int L = 0;
        int R= 0;
        while (L < arr.length) {
            while (R < arr.length) {
                //当窗口内有元素，并且末尾元素小于当前值  弹出
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                //当窗口内有元素，并且末尾元素大于当前值  弹出
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            //退出循环的时候是 第一个不满足的R
            //如果0...i 都可以  那么 0.0，0..1 ，0..2 ,0..3,...., 0...i-1 都可以
            res += R - L;
            //左边窗口向右移，如果右边仍为 最值 则弹出
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            L++;
        }

        return res;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(getNum(arr, num));

    }
}
