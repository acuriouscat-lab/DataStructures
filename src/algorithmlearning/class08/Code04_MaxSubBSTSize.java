package algorithmlearning.class08;

import java.util.ArrayList;
///给定一棵二叉树的头节点head，
//返回这颗二叉树中最大的二叉搜索子树的大小
public class Code04_MaxSubBSTSize {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    // 任何子树
    public static class Info {
        public boolean isAllBST;
        public int maxSubBSTSize;
        public int min;
        public int max;

        public Info(boolean is, int size, int mi, int ma) {
            isAllBST = is;
            maxSubBSTSize = size;
            min = mi;
            max = ma;
        }
    }

    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSubBSTSize;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);

        int max = head.value;
        int min = head.value;
        int maxSubBSTSize = 0;

        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            maxSubBSTSize = Math.max(maxSubBSTSize, left.maxSubBSTSize);
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            maxSubBSTSize = Math.max(maxSubBSTSize, right.maxSubBSTSize);
        }

        boolean isALLBST = false;

        if (
                (left == null || (left.isAllBST && left.max < head.value))
                        && (right == null || (right.isAllBST && right.min > head.value))
        ) {
            maxSubBSTSize = (left == null ? 0 : left.maxSubBSTSize) +
                    (right == null ? 0 : right.maxSubBSTSize) + 1;
            isALLBST = true;
        }
        return new Info(isALLBST, maxSubBSTSize, min, max);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
