package alg01.class03;

public class Code02_DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeValue(Node head, int num) {
        Node dummy = new Node(num + 1);
        dummy.next = head;

        Node cur = dummy;
        Node pre = dummy;
        while (cur != null) {
            if (cur.value != num) {
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return  dummy.next;
    }

}
