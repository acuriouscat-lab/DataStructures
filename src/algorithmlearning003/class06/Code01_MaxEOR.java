package algorithmlearning003.class06;

public class Code01_MaxEOR {

    //一个数组的异或和是指数组中所有的数异或在一起的结果
    //给定一个数组arr，求最大子数组异或和。
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        // 生成eor数组，eor[i]代表arr[0..i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) { // 以j位置结尾的情况下，每一个子数组最大的异或和
            for (int i = 0; i <= j; i++) { // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }


    public static class Node{
        public Node[] next = new Node[2];
    }

    // 基于本题，定制前缀树的实现
    public static class NumTrie{
        public Node head = new Node();
        public void add(int num) {
            Node cur = head;
            // 把某个数字newNum加入到这棵前缀树里
            // num是一个32位的整数，所以加入的过程一共走32步
            for (int move = 31; move >= 0; move--) {
                // 从高位到低位，取出每一位的状态，如果当前状态是0，
                // path(int) = 0
                // ，如果当前状态是1
                // path(int) = 1
                int path = ((num >> move) & 1);
                cur.next[path] = cur.next[path] == null ? new Node() : cur.next[path];

                cur = cur.next[path];

            }
        }

        // 该结构之前收集了一票数字，并且建好了前缀树
        // eori,和 ？ ^  最大的结果（返回）
        public int maxXor(int eori) {
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                //当前位的值
                int path = (eori >> move) & 1;
                //期待位异或的值 第一位相同 其他位相反
                int best = move == 31 ? path : (path ^ 1);
                //实际走的路
                best = cur.next[best] == null ? (best ^ 1) : best;
                // 当前位异或之后 结果在当前位的值
                res |= (best ^ path) << move;
                //来到下一个结点
                cur = cur.next[best];
            }
            return res;
        }
    }

    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        int eor = 0;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            res = Math.max(res,numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return res;

    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random())
                    - (int) (maxValue * Math.random());
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
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = maxXorSubarray1(arr);
            int res = maxXorSubarray2(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
        //
        // // int[] arr = generateRandomArray(6, maxValue);
        // int[] arr = { 3, -28, -29, 2};
        //
        // for (int i = 0; i < arr.length; i++) {
        // System.out.println(arr[i] + " ");
        // }
        // System.out.println("=========");
        // System.out.println(maxXorSubarray(arr));
        // System.out.println((int) (-28 ^ -29));

    }


}
