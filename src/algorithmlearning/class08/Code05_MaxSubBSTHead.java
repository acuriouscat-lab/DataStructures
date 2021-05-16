package algorithmlearning.class08;

import java.util.ArrayList;
//给定一棵二叉树的头节点head，
//返回这颗二叉树中最大的二叉搜索子树的头节点
public class Code05_MaxSubBSTHead {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> ans = new ArrayList<>();
        in(head, ans);
        for (int i = 1; i < ans.size(); i++) {
            if (ans.get(i - 1).value >= ans.get(i).value) {
                return 0;
            }
        }
        return ans.size();
    }

    public static void in(Node head, ArrayList<Node> ans) {
        if (head == null) {
            return;
        }
        in(head.left, ans);
        ans.add(head);
        in(head.right, ans);
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }

        Node leftNode = maxSubBSTHead1(head.left);
        Node rightNode = maxSubBSTHead1(head.right);

        return getBSTSize(leftNode) >= getBSTSize(rightNode) ? leftNode : rightNode;
    }

    public static class Info{
        public int max;
        public int min;
        public int maxSubBSTSize;
        public Node maxSubBSTHead;

        public Info(int max, int min, int maxSubBSTSize, Node maxSubBSTHead) {
            this.max = max;
            this.min = min;
            this.maxSubBSTSize = maxSubBSTSize;
            this.maxSubBSTHead = maxSubBSTHead;
        }
    }

    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxSubBSTHead;
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
        Node maxSubBSTHead = null;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            maxSubBSTSize = Math.max(maxSubBSTSize, left.maxSubBSTSize);
            maxSubBSTHead = left.maxSubBSTHead;
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            if (right.maxSubBSTSize > maxSubBSTSize) {
                maxSubBSTHead = right.maxSubBSTHead;
                maxSubBSTSize = right.maxSubBSTSize;
            }
        }

        if (
                (left == null || (left.maxSubBSTHead == head.left && left.max < head.value))
                        && (right == null || right.maxSubBSTHead == head.right && right.min > head.value)
        ) {
            maxSubBSTHead = head;
            maxSubBSTSize = (left == null ? 0 : left.maxSubBSTSize)
                    + (right == null ? 0 : right.maxSubBSTSize) + 1;
        }

        return new Info(max, min, maxSubBSTSize, maxSubBSTHead);
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
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
