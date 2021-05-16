package algorithmlearning.class07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code_TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //求二叉树最宽的层有多少个节点
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> levelMap = new HashMap<>();//用来存储结点在第几层
        int curLevel = 1; //当前层
        int curLevelNode = 0; //当前层有几个
        int max = 0 ; //最大宽度
        levelMap.put(head, 1);
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNode++;
            }else{
                max = Math.max(max, curLevelNode);
                curLevel++;
                curLevelNode = 1;
            }
        }
        max = Math.max(curLevelNode, max);
        return max;
    }


    public static int maxWidthNoMap(Node head) {
        if(head == null){ return 0;}
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;//当前层的最后一个节点
        Node nextEnd = null;//下一层的最右节点
        int curLevelNode = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNode++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNode);
                curLevelNode = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }


    public static int maxWidthNoMap1(Node head) {
        if(head == null) return  0;
        Queue<Node> queue = new LinkedList<>();
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNode = 0;
        queue.add(head);
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNode++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNode);
                curEnd = nextEnd;
                curLevelNode = 0;
            }
        }
        return max;
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
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap1(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }


}
