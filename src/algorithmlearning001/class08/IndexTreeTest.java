package algorithmlearning001.class08;

public class IndexTreeTest {

    // 一种数据结构 树状数组
    // 提供在单点修改 和 1。。。n 的累加和
    // 复杂度都是logn
    /*
   「树状数组」是一种可以动态维护序列前缀和的数据结构，它的功能是：
        单点更新 update(i, v)： 把序列 iii 位置的数加上一个值 vvv，这题 v=1v = 1v=1
        区间查询 query(i)： 查询序列 [1⋯i][1 \cdots i][1⋯i] 区间的区间和，即 iii 位置的前缀和
        修改和查询的时间代价都是 O(log⁡n)O(\log n)O(logn)，其中 nnn 为需要维护前缀和的序列的长度。
     */
    public static class IndexTree {

        public int size;
        public int[] arr;

        public IndexTree(int size) {
            this.size = size;
            // 0 位置不用
            // 1.。。index 计算才能成立
            arr = new int[size + 1];
        }

        public void add(int index, int val) {
            while (index <= size) {/**/
                arr[index] += val;
                // 每一次二进制末尾的1 加上一个1
                index += index & -index;
            }
        }

        // 1...index 的累加和
        // 二进制下 把 1 拆开 + 1 到他自己
        public int sum(int index) {
            int ret = 0;
            while (index >= 1) {
                ret += arr[index];
                index -= index & -index;
            }
            return ret;
        }
    }

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }


}
