package algorithmlearning003.class02;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_TrappingRainWaterII {


    //如果给你一个二维数组，每一个值表示这一块地形的高度，
    //求整块地形能装下多少水。
    public static class Node {
        public int value;
        public int row;
        public int col;

        public Node(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    //如果给你一个二维数组，每一个值表示这一块地形的高度，
    //求整块地形能装下多少水。
    public static int trapRainWater(int[][] arr) {
        if (arr == null || arr.length < 3 || arr[0].length < 3) {
            return 0;
        }
        int r = arr.length;
        int c = arr[0].length;
        //小根堆
        PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator());
        // 避免重复进入
        boolean[][] isEntered = new boolean[r][c];
        //将四周的点封装成node结点到小根堆中
        // 并且在isEntered中标记以进入
        for (int i = 0; i < r; i++) {
            queue.add(new Node(arr[i][0], i, 0));
            isEntered[i][0] = true;
        }
        for (int i = 0; i < r; i++) {
            queue.add(new Node(arr[i][c - 1], i, c - 1));
            isEntered[i][c - 1] = true;
        }
        for (int i = 1; i < c - 1; i++) {
            queue.add(new Node(arr[0][i], 0, i));
            isEntered[0][i] = true;
        }
        for (int i = 1; i < c - 1; i++) {
            queue.add(new Node(arr[r - 1][i], r - 1, i));
            isEntered[r - 1][i] = true;
        }
        int water = 0;
        int max = 0;
        while (!queue.isEmpty()) {//小根堆
            Node poll = queue.poll();
            int row = poll.row;
            int col = poll.col;
            max = Math.max(max, poll.value);
            // 将周围的点加入
            if (row > 0 && !isEntered[row - 1][col]) {
                water += Math.max(0, max - arr[row - 1][col]);
                queue.add(new Node(arr[row - 1][col], row - 1, col));
                isEntered[row - 1][col] = true;
            }
            if (row < r - 1 && !isEntered[row + 1][col]) {
                water += Math.max(0, max - arr[row + 1][col]);
                queue.add(new Node(arr[row + 1][col], row + 1, col));
                isEntered[row + 1][col] = true;
            }
            if (col > 0 && !isEntered[row][col - 1]) {
                water += Math.max(0, max - arr[row][col - 1]);
                queue.add(new Node(arr[row][col - 1], row, col - 1));
                isEntered[row][col - 1] = true;
            }
            if (col < c - 1 && !isEntered[row][col + 1]) {
                water += Math.max(0, max - arr[row][col + 1]);
                queue.add(new Node(arr[row][col + 1], row, col + 1));
                isEntered[row][col + 1] = true;
            }
        }

        return water;


    }

    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 6);
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(3, 4);
        printMatrix(matrix);
        System.out.println(trapRainWater(matrix));
    }


}
