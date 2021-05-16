package algorithmlearning.class08;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Code07_lowestAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //给定一棵二叉树的头节点head，和另外两个节点a和b。
    //返回a和b的最低公共祖先
    public static Node lowestAncestor1(Node head,Node o1,Node o2) {
        if (head ==  null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        map.put(o1, null);
        fillParentMap(head, map);
        HashSet<Node> set = new HashSet<>();
        Node cur = o1;
        set.add(o1);
        while ((cur = map.get(cur)) != null) {
            set.add(cur);
        }
        cur = o2;
        while (!set.contains(cur) && cur != null) {
            cur = map.get(cur);
        }
        return cur;
    }

    private static void fillParentMap(Node o1, HashMap<Node, Node> map) {
        if (o1.left != null) {
            map.put(o1.left, o1);
            fillParentMap(o1.left, map);
        }
        if (o1.right != null) {
            map.put(o1.right, o1);
            fillParentMap(o1.right, map);
        }
    }
    //DFS
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if(root == null) return null; // 如果树为空，直接返回null
        if(root == p || root == q) return root; // 如果 p和q中有等于 root的，那么它们的最近公共祖先即为root（一个节点也可以是它自己的祖先）
        Node left = lowestCommonAncestor(root.left, p, q); // 递归遍历左子树，只要在左子树中找到了p或q，则先找到谁就返回谁
        Node right = lowestCommonAncestor(root.right, p, q); // 递归遍历右子树，只要在右子树中找到了p或q，则先找到谁就返回谁
        if(left == null) return right; // 如果在左子树中 p和 q都找不到，则 p和 q一定都在右子树中，右子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
        else if(right == null) return left; // 否则，如果 left不为空，在左子树中有找到节点（p或q），这时候要再判断一下右子树中的情况，如果在右子树中，p和q都找不到，则 p和q一定都在左子树中，左子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
        else return root; //否则，当 left和 right均不为空时，说明 p、q节点分别在 root异侧, 最近公共祖先即为 root
    }

    public static Node lowestAncestor2(Node head, Node o1, Node o2) {
        return process(head, o1, o2).ans;
    }

    private static Info process(Node head, Node o1, Node o2) {
        if (head == null) {
            return new Info(null ,false,false);
        }
        Info left = process(head.left, o1, o2);
        Info right = process(head.right, o1, o2);

        boolean findO1 = head == o1 || left.findO1 || right.findO1;
        boolean findO2 = head == o2 || right.findO2 || left.findO2;

        Node ans = null;

        if (left.ans != null) {
            ans = left.ans;
        }

        if (right.ans != null) {
            ans = right.ans;
        }

        if (ans == null && findO1 && findO2) {
            ans = head;
        }

        return new Info(ans, findO1, findO2);
    }

    public static class Info {
        public Node ans;
        public boolean findO1;
        public boolean findO2;

        public Info(Node a, boolean f1, boolean f2) {
            ans = a;
            findO1 = f1;
            findO2 = f2;
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
