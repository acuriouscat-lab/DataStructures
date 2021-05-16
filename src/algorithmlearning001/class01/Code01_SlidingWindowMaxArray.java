package algorithmlearning001.class01;

import java.util.Arrays;
import java.util.LinkedList;

public class Code01_SlidingWindowMaxArray {
    /*
        想用滑动窗口，要想办法把具体的问题转化为滑动窗口的处理流程
        想用滑动窗口最值的更新结构，就看看处理流程下，是否需要最值这个信息

        想用单调栈，要想办法把具体的问题转化为单调栈所解决的原问题
        滑动窗口及其最大值和最小值的更新结构、单调栈，都是重要算法原型
     */
    /*
        假设一个固定大小为W的窗口，依次划过arr，
        返回每一次滑出状况的最大值
        例如，arr = [4,3,5,4,3,3,6,7], W = 3
        返回：[5,5,5,4,6,7]
     */

    /*
        窗口不管L还是R滑动之后，都会让窗口呈现新状况，
        如何能够更快的得到窗口当前状况下的最大值和最小值？
        最好平均下来复杂度能做到O(1)
        利用单调双端队列！
     */
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> queue = new LinkedList<>();//链表存储的是下标
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            //当链表有元素并且 链表尾部元素比当前元素小时，弹出尾部元素
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            //一直没有弹出，并且达到窗口的大小的时候，弹出头部元素
            if (queue.peekFirst() == i - w) {
                queue.pollFirst();
            }
            //需要记录数据的时候
            if (i >= w - 1) {
                res[index++] = arr[queue.peekFirst()];
            }
        }

        return res;
    }

    public static int[] getMinWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!queue.isEmpty() && arr[i] < arr[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.addLast(i);

            if (queue.peekFirst() == i - w) {
                queue.pollFirst();
            }

            if (i >= w - 1) {
                res[index++] = arr[queue.peekFirst()];
            }
        }

        return res;

    }



    // for test
    public static int[] rightWay(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < arr.length) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);

            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = rightWay(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");

        int[] arr = {1, 3, 4, 5, 6, 3, 6, 2, 9, 2};
        int[] minWindow = getMinWindow(arr, 3);
        System.out.println(Arrays.toString(minWindow));

    }






}
