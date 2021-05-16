package algorithmlearning.class08;

import java.util.ArrayList;

public class Code03_isBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //给定一棵二叉树的头节点head，返回这颗二叉树是不是搜索二叉树
    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> ans = new ArrayList<>();
        in(head, ans);
        for (int i = 1; i < ans.size(); i++) {
            if (ans.get(i - 1).value >= ans.get(i).value) {
                return false;
            }
        }
        return true;
    }

    private static void in(Node head, ArrayList<Node> ans) {
        if (head == null) {
            return;
        }
        in(head.left, ans);
        ans.add(head);
        in(head.right, ans);
    }

    public static class Info{
        public int max ;
        public int min;
        public boolean isBST;

        public Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);

        int max = head.value;
        int min = head.value;

        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }

        boolean isBST = false;
        if (
                (left == null || (left.isBST && left.max < head.value))
                        && (right == null || (right.isBST && right.min > head.value))
        ) {
            isBST = true;
        }
        return new Info(max, min, isBST);
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
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
