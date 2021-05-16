package algorithmlearning001.class01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code03_MonotonousStack {

    //一种特别设计的栈结构，为了解决如下的问题：
    //给定一个可能含有重复值的数组arr，i位置的数一定存在如下两个信息
    //1）arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪？
    //2）arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪？
    //如果想得到arr中所有位置的两个信息，怎么能让得到信息的过程尽量快。
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer popIndex = stack.pop();
                res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            res[pop][1] = -1;
        }
        return res;
    }

    //单调栈
    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        //存储的也是下标
        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        for (int i = 0; i < arr.length; i++) {
            //栈不为空，并且栈顶的元素比当前元素大
            //栈顶元素的信息
            //左边小于当前数的下标为：1）栈为空时-1  2）栈不为空时为栈顶数组的最后一个
            //右边为 当前元素
            // 底 -> 顶， 小 -> 大
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> pop = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popi : pop) {
                    res[popi][0] = leftIndex;
                    res[popi][1] = i;
                }
            }
            //如果栈不为空  或者  栈顶的元素等于当前值
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            }else{//如果栈为空  或者  栈顶的元素小于当前值
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        // 还有数
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popi : pop) {
                res[popi][0] = leftIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
    }
}





















