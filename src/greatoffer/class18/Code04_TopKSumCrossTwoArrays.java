package greatoffer.class18;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Administrator
 * @Description https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1
 * @create 2021-07-19 20:12
 */
public class Code04_TopKSumCrossTwoArrays {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = sc.nextInt();
        }
        for (int j = 0; j < n; j++) {
            arr2[j] = sc.nextInt();
        }
        int[] res = topK(arr1, arr2, k);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public static class Node {
        int idx1;
        int idx2;
        int val;

        public Node(int i1, int i2, int v) {
            idx1 = i1;
            idx2 = i2;
            val = v;
        }
    }

    public static int[] topK(int[] arr1, int[] arr2, int topK) {
        if (arr1 == null || arr2 == null || topK < 1) {
            return null;
        }
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);
        int idx1 = arr1.length - 1;
        int idx2 = arr2.length - 1;
        int M = arr2.length;
        HashSet<Long> set = new HashSet<>();
        set.add(getidx(idx1, idx2, M));
        int[] res = new int[Math.min(topK, arr1.length * arr2.length)];
        int index = 0;
        heap.add(new Node(idx1, idx2, arr1[idx1] + arr2[idx2]));
        while (topK != 0 && !heap.isEmpty()) {
            Node node = heap.poll();
            res[index++] = node.val;
            topK--;
            idx1 = node.idx1;
            idx2 = node.idx2;
            set.remove(getidx(idx1, idx2, M));
            if (idx1 > 0 && !set.contains(getidx(idx1 - 1, idx2, M))) {
                heap.add(new Node(idx1 - 1, idx2, arr1[idx1 - 1] + arr2[idx2]));
                set.add(getidx(idx1 - 1, idx2, M));
            }
            if (idx2 > 0 && !set.contains(getidx(idx1, idx2 - 1, M))) {
                heap.add(new Node(idx1, idx2 - 1, arr1[idx1] + arr2[idx2 - 1]));
                set.add(getidx(idx1, idx2 - 1, M));
            }
        }
        return res;
    }

    public static long getidx(int i, int j, int M) {
        return (long) i * (long) M + (long) j;
    }


}
