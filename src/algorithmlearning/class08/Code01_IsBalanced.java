package algorithmlearning.class08;

//给定一棵二叉树的头节点head，返回这颗二叉树是不是平衡二叉树
public class Code01_IsBalanced {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBalanced1(Node head) {
        boolean[] res = new boolean[1];
        res[0] = true;
        process1(res, head);
        return res[0];
    }

    private static int process1(boolean[] res, Node head) {
        if (!res[0] || head == null) {
            return 0;
        }
        int leftHeight = process1(res, head.left);
        int rightHeight = process1(res, head.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            res[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
    //判断出左右子树都需要哪些信息才能进行判断
    public static class Info {
        public int height;
        public boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }

    }

    public static boolean isBalanced2(Node head) {
        return process2(head).isBalanced;
    }

    private static Info process2(Node head) {
        if (head == null) {//得出base case 的信息
            return new Info(0, true);
        }
        //根据左右子树的信息加工出自己的信息然后返回自己的信息
        Info left = process2(head.left);
        Info right = process2(head.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean isBalanced = true;
        if (!left.isBalanced || !right.isBalanced || Math.abs(left.height - right.height) > 1) {
            isBalanced = false;
        }
        return new Info(height, isBalanced);
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
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}



