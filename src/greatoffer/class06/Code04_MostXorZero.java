package greatoffer.class06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Code04_MostXorZero {

    //数组中所有数都异或起来的结果，叫做异或和
    //给定一个数组arr，可以任意切分成若干个不相交的子数组
    //其中一定存在一种最优方案，使得切出异或和为0的子数组最多
    //返回这个最多数量

    // 暴力解
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] eor = new int[N];
        eor[0] = arr[0];
        for (int i = 1; i < N; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        return process(eor, 1, new ArrayList<>());
    }

    // index去决定：前一坨部分，结不结束！
    // 如果结束！就把index放入到parts里去
    // 如果不结束，就不放
    public static int process(int[] eor, int index, List<Integer> path) {
        int ans = 0;
        if (index == eor.length) {
            path.add(eor.length);
            ans = eorZeroParts(path, eor);
            path.remove(path.size() - 1);
        }else{
            int p1 = process(eor, index + 1, path);
            path.add(index);
            int p2 = process(eor, index + 1, path);
            path.remove(path.size() - 1);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    public static int eorZeroParts(List<Integer> path, int[] eor) {
        int L = 0;
        int ans = 0;
        for (Integer end : path) {
            if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
                ans++;
            }
            L = end;
        }
        return ans;
    }

    public static int mostXor(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            if (map.containsKey(eor)) {
                int pre = map.get(eor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(eor, i);
        }
        return dp[N - 1];
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 150000;
        int maxSize = 12;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostXor(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


}
