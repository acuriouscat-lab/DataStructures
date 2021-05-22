package greatoffer.class06;

public class Code01_MaxXOR {

    //数组中所有数都异或起来的结果，叫做异或和
    //给定一个数组arr，返回arr的最大子数组异或和
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] eor = new int[N];
        eor[0] = arr[0];
        for (int i = 1; i < N; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                max = Math.max(max, (i == 0 ? eor[j] : eor[j] ^ eor[i - 1]));
            }
        }
        return max;
    }

    public static class Node{
        public Node[] nexts  = new Node[2];
    }

    public static class NumTrie {

        public Node head = new Node();

        public void add(int num) {
            Node node = head;
            for (int i = 31; i >= 0; i--) {
                int path = ((num >> i) & 1);
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
            }
        }

        public int maxEor(int num) {
            Node node = head;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                int best = (i == 31 ? path : (path ^ 1));
                best = node.nexts[best] == null ? (best ^ 1) : best;
                ans |= ((best ^ path) << i);
                node = node.nexts[best];
            }
            return ans;
        }


    }

    //  如果知道哪一条前缀和当前 ^ 出来能够最大就好了？
    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int eor = 0;
        NumTrie trie = new NumTrie();
        trie.add(0);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            max = Math.max(max, trie.maxEor(eor));
            trie.add(eor);
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
    }

}
