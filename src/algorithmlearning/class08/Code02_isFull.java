package algorithmlearning.class08;
//给定一棵二叉树的头节点head，返回这颗二叉树是不是满二叉树
public class Code02_isFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //非套路版本 2^L -1 = N
    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    private static int n(Node head) {
        if (head == null) {
            return 0;
        }
        int leftN = n(head.left);
        int rightN = n(head.right);
        return leftN + rightN + 1;
    }

    private static int h(Node head) {
        if (head == null) {
            return 0;
        }
        int leftH = h(head.left);
        int rightH = h(head.right);
        return Math.max(leftH, rightH) + 1;
    }

    //根据左右子树需要的信息判断
    public static class Info{
        public int height;
        public int node;

        public Info(int height, int node) {
            this.height = height;
            this.node = node;
        }
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        Info all = process(head);
        return (1 << all.height) - 1 == all.node;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int height = Math.max(left.height, right.height) +1;
        int nodes = left.node + right.node +1;
        return new Info(height, nodes);
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
