package greatoffer.class10;

public class Code04_BSTtoDoubleLinkedList {

    //https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //给定一棵搜索二叉树头节点，转化成首尾相接的有序双向链表
    public static Node treeToDoublyList(Node head) {
        if(head == null){
            return null;
        }
        Info info = process(head);
        info.tail.right = info.head;
        info.head.left = info.tail;
        return info.head;
    }

    public static Info process(Node node){
        if(node == null){
            return new Info(null,null);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        if (leftInfo.tail != null) {
            leftInfo.tail.right = node;
        }
        node.left = leftInfo.tail;
        node.right = rightInfo.head;
        if (rightInfo.head != null) {
            rightInfo.head.left = node;
        }
        return new Info(leftInfo.head != null ? leftInfo.head : node, rightInfo.tail != null ? rightInfo.tail : node);
    }

    public static class Info{
        public Node head;
        public Node tail;

        public Info(Node h, Node t) {
            head = h;
            tail = t;
        }
    }


}
