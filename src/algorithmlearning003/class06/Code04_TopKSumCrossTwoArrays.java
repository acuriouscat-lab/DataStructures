package algorithmlearning003.class06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_TopKSumCrossTwoArrays {

    //给定两个有序数组arr1和arr2，再给定一个正数K
    //求两个数累加和最大的前K个，两个数必须分别来自arr1和arr2
    public static class Node{

        int index1;
        int index2;
        int sum;

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }

    }


    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    public static int[] topKSum(int[] arr1, int[] arr2, int k) {

        if (arr1 == null || arr2 == null || k < 1) {
            return null;
        }

        int topk = Math.min(k, arr1.length * arr2.length);

        int[] res = new int[topk];

        PriorityQueue<Node> queue = new PriorityQueue<>(new NodeComparator());

        int index1 = arr1.length - 1;
        int index2 = arr2.length - 1;

        boolean[][] set = new boolean[arr1.length][arr2.length];
        queue.add(new Node(index1, index2, arr1[index1] + arr2[index2]));
        set[index1][index2] = true;

        int resIndex = 0;



        while (topk != resIndex) {

            Node node = queue.poll();
            int i1 = node.index1;
            int i2 = node.index2;
            res[resIndex++] = node.sum;

            if (i1 - 1 >= 0 && !set[i1 - 1][i2]) {
                set[i1 - 1][i2] = true;
                queue.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
            }

            if (i2 - 1 >= 0 && !set[i1][i2 - 1]) {
                set[i1][i2 - 1] = true;
                queue.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
            }

        }

        return res;



    }


    // For test, this method is inefficient but absolutely right
    public static int[] topKSumTest(int[] arr1, int[] arr2, int topK) {
        int[] all = new int[arr1.length * arr2.length];
        int index = 0;
        for (int i = 0; i != arr1.length; i++) {
            for (int j = 0; j != arr2.length; j++) {
                all[index++] = arr1[i] + arr2[j];
            }
        }
        Arrays.sort(all);
        int[] res = new int[Math.min(topK, all.length)];
        index = all.length - 1;
        for (int i = 0; i != res.length; i++) {
            res[i] = all[index--];
        }
        return res;
    }

    public static int[] generateRandomSortArray(int len) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * 50000) + 1;
        }
        Arrays.sort(res);
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i != arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int a1Len = 5000;
        int a2Len = 4000;
        int k = 20000000;
        int[] arr1 = generateRandomSortArray(a1Len);
        int[] arr2 = generateRandomSortArray(a2Len);
        long start = System.currentTimeMillis();
        int[] res = topKSum(arr1, arr2, k);
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        start = System.currentTimeMillis();
        int[] absolutelyRight = topKSumTest(arr1, arr2, k);
        end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        System.out.println(isEqual(res, absolutelyRight));

    }




}
