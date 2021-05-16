package algorithmlearning001.class01;

import java.util.Stack;

public class Code04_AllTimesMinToMax {
    //给定一个只包含正数的数组arr，arr中任何一个子数组sub，
    //一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
    //那么所有子数组中，这个值最大是多少？
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum +=arr[k];
                    min = Math.min(min, arr[k]);
                }
                max = Math.max(sum * min, max);
            }
        }
        return max;
    }


    public static int max2(int[] arr) {
        int size = arr.length;
        int[] sum = new int[size];
        //预处理手段  得到 0-i的累加和 sum[i]
        //区间的累加和  sum[i] - sum[j]
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            //以当前i为最小  最大能扩到哪里
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                //结算当前位置的(sub累加和 )* (sub中的最小值)
                max = Math.max(max, (stack.isEmpty() ? sum[i - 1] : (sum[i - 1] - sum[stack.peek()]))* arr[j] );
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sum[size - 1] : (sum[size - 1] - sum[stack.peek()]) )* arr[j]);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }

}
