package algorithmlearning.class08;

import java.util.LinkedList;
import java.util.Queue;

//给定一棵二叉树的头节点head，返回这颗二叉树中是不是完全二叉树
public class Code06_IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //一旦遇到结点的左右孩子不双全，则接下来的结点都必须是叶子节点
    private static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        boolean flag = false;
        Node l = null;
        Node r = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            l = node.left;
            r = node.right;
            if ((flag && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                flag = true;
            }
        }
        return true;
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process1(head).isCBT;
    }

    private static Info process1(Node head) {
        if (head == null) {
            return new Info(0,true,true);
        }
        Info leftInfo = process1(head.left);
        Info rightInfo = process1(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height);
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        }else {
            if (leftInfo.isCBT && rightInfo.isCBT) {
                if ( leftInfo.isCBT && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
            }
        }
        return new Info(height,isCBT,isFull);
    }

    public static class Info{
        public int height;
        public boolean isCBT;
        public boolean isFull;

        public Info( int height, boolean isCBT, boolean isFull) {
            this.height = height;
            this.isCBT = isCBT;
            this.isFull = isFull;
        }
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
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }




}
