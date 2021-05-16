package algorithmlearning.class03;

public class Code02_DeleteGivenValue {

    //把给定值都删除
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node removeValue(Node head, int num) {
        //头节点开始如果是的话
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        //来到了第一个不需要删除的节点
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
