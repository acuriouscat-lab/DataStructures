package algorithmlearning003.class04;

public class Code02_BSTtoDoubleLinkedList {

    //双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是next的话。
    //给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node convert2(Node head) {
        if (head == null) {
            return null;
        }

        return null;
    }

    public static class Info{
        Node start;
        Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    // 以x为头的整棵搜索二叉树，请全部以有序双向链表的方式，连好
    // 并且返回，整个有序双向链表的头节点和尾节点
    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info righrInfo = process(node.right);

        if (leftInfo != null) {
            leftInfo.end.right = node;
        }
        if (righrInfo != null) {
            righrInfo.start.left = node;
        }

        return new Info(
                leftInfo.start != null ? leftInfo.start : node
                , righrInfo.end != null ? righrInfo.end : node
        );

    }

}
