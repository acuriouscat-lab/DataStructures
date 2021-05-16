package algorithmlearning.class06;
/*
        将单向链表按某值划分成左边小、中间相等、右边大的形式
 */
public class Code_SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //把链表放入数组里，在数组上做partition（笔试用）
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        int count = 0;
        Node cur = head;
        while (cur != null) {//统计出链表的长度
            count++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[count];//用于放链表节点的数组
        cur = head;
        for (int i = 0; i < count; i++) {//将节点放进数组
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr,pivot);//在数组内做partition
        for (count = 1; count != nodeArr.length ; count++) {//节点重新连接
            nodeArr[count-1].next = nodeArr[count];
        }
        nodeArr[count - 1].next = null;
        return nodeArr[0];
    }

    private static void arrPartition(Node[] nodeArr,int pivot) {
        int samll = -1;
        int larget = nodeArr.length;
        int index = 0;
        while (index != larget) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++samll, index++);
            } else if (nodeArr[index].value > pivot) {
                swap(nodeArr, --larget, index);
            }else{
                index ++;
            }
        }
    }

    private static void swap(Node[] nodeArr, int a, int b) {
        Node temp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = temp;
    }

    //分成小、中、大三部分，再把各个部分之间串起来（面试用）
    public static Node listPartition2(Node head, int pivot) {
        if (head == null) {
            return  null;
        }
        Node sH = null; // small head
        Node sT = null; //samll tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node bH = null; //big head
        Node bT = null; // big tail
        Node next = null; // save the next node
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    sT = sT.next;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = eT.next;
                }
            }else{
                if (bH == null) {
                    bH = head;
                    bT = head;
                }else{
                    bT.next = head;
                    bT = bT.next;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;// 下一步，谁去连大于区域的头，谁就变成eT
        }
        if (eT != null) {// 如果小于区域和等于区域，不是都没有
            eT.next = bH;
        }
        return sH = sH != null ? sH : ( eH != null ? eH : bH);

    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
         head1 = listPartition1(head1, 8);
       // head1 = listPartition2(head1, 4);
        printLinkedList(head1);

    }



}



