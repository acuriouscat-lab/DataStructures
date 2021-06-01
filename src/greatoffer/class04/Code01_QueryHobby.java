package greatoffer.class04;

import java.util.ArrayList;
import java.util.HashMap;

public class Code01_QueryHobby {

    /*
     * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)。意思是在数组里下标0~3这个范围上，有几个2？返回2。
     * 假设给你一个数组arr，对这个数组的查询非常频繁，请返回所有查询的结果
     */
    public static class QueryBox2 {

        HashMap<Integer, ArrayList<Integer>> map;

        public QueryBox2(int[] arr) {
            map = new HashMap<>();

            for (int i = 0; i < arr.length; i++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], new ArrayList<>());
                }
                map.get(arr[i]).add(i);
            }

        }

        public int query(int L, int R, int val) {
            if (!map.containsKey(val)) {
                return 0;
            }
            ArrayList<Integer> list = map.get(val);
            int left = countless(list, L);
            int right = countless(list, R + 1);
            return right - left;
        }

        /**
         * 找到 < val 最右边的位置
         *
         * @param list
         * @param val
         * @return
         */
        public int countless(ArrayList<Integer> list, int val) {
            int ans = -1;
            int l = 0;
            int r = list.size() - 1;
            while (l <= r) {
                int m = (l + r) >> 1;
                if (list.get(m) >= val) {
                    r = m - 1;
                } else {
                    ans = m;
                    l = m + 1;
                }
            }
            return ans;
        }


    }

    public static class QueryBox1 {
        private int[] arr;

        public QueryBox1(int[] array) {
            arr = new int[array.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = array[i];
            }
        }

        public int query(int L, int R, int v) {
            int ans = 0;
            for (; L <= R; L++) {
                if (arr[L] == v) {
                    ans++;
                }
            }
            return ans;
        }
    }

    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 300;
        int value = 20;
        int testTimes = 1000;
        int queryTimes = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int N = arr.length;
            QueryBox1 box1 = new QueryBox1(arr);
            QueryBox2 box2 = new QueryBox2(arr);
            for (int j = 0; j < queryTimes; j++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                int L = Math.min(a, b);
                int R = Math.max(a, b);
                int v = (int) (Math.random() * value) + 1;
                if (box1.query(L, R, v) != box2.query(L, R, v)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test end");
    }

}
