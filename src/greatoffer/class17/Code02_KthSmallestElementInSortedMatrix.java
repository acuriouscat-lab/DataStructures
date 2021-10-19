package greatoffer.class17;

import java.util.PriorityQueue;

/**
 * @author Administrator
 * @Description https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * @create 2021-07-15 11:24
 */
public class Code02_KthSmallestElementInSortedMatrix {


    public static class Node {
        int val;
        int row;
        int col;

        public Node(int v, int r, int c) {
            val = v;
            row = r;
            col = c;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {

        if (matrix == null || matrix.length == 0) {
            return -1;
        }

        int N = matrix.length;
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        heap.add(new Node(matrix[0][0], 0, 0));
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            if (--k == 0) {
                return cur.val;
            }
            int row = cur.row;
            int col = cur.col;
            if (row + 1 < N && !visited[row + 1][col]) {
                heap.add(new Node(matrix[row + 1][col], row + 1, col));
                visited[row + 1][col] = true;
            }
            if (col + 1 < N && !visited[row][col + 1]) {
                heap.add(new Node(matrix[row][col + 1], row, col + 1));
                visited[row][col + 1] = true;
            }
        }
        return -1;

    }


    // 二分的方法
    public static int kthSmallest2(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[N - 1][M - 1];
        int ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // <=mid 有几个 <= mid 在矩阵中真实出现的数，谁最接近mid
            Info info = noMoreNum(matrix, mid);
            if (info.num < k) {
                left = mid + 1;
            } else {
                ans = info.near;
                right = mid - 1;
            }
        }
        return ans;
    }

    public static class Info {
        public int near;
        public int num;

        public Info(int n1, int n2) {
            near = n1;
            num = n2;
        }
    }

    public static Info noMoreNum(int[][] matrix, int value) {
        int near = Integer.MIN_VALUE;
        int num = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int row = 0;
        int col = M - 1;
        while (row < N && col >= 0) {
            if (matrix[row][col] <= value) {
                near = Math.max(near, matrix[row][col]);
                num += col + 1;
                row++;
            } else {
                col--;
            }
        }
        return new Info(near, num);
    }


}
