package algorithmlearning001.class06;

public class Code01_MorrisTraversal {
    //假设来到当前节点cur，开始时cur来到头节点位置
    //1）如果cur没有左孩子，cur向右移动(cur = cur.right)
    //2）如果cur有左孩子，找到左子树上最右的节点mostRight：
    //	a.如果mostRight的右指针指向空，让其指向cur，
    //  	然后cur向左移动(cur = cur.left)
    //	b.如果mostRight的右指针指向cur，让其指向null，
    //  	然后cur向右移动(cur = cur.right)
    //3）cur为空时遍历停止

    /*
    什么时候是 ？
            如果需要左树给你什么信息右树给你什么信息   就不能用morris  不能做到空间复杂度为O(1)
            如果只需要一次信息 后序不需要就可以-----不需要全信息
     */
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    //一种遍历二叉树的方式，并且时间复杂度O(N)，额外空间复杂度O(1)
    //通过利用原树中大量空闲指针的方式，达到节省空间的目的

    //中序便利  == 》 第二次来到节点的时候
    public static void morrisIn(Node head) {
        if( head == null ) return ;
        Node cur = head;//当前节点
        Node mostRight = null;//当前节点左树的最右节点
        while (cur != null) {
            mostRight = cur.left;
            if(mostRight != null){//当前节点左树不为空->可以来到两次的节点
                while (mostRight.right != null && mostRight.right != cur) {//找到最右节点
                    mostRight = mostRight.right;
                }
                //将右节点指向当前节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{//第二次的时候为空 == mostRight.right == cur
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }
    //先序遍历  --- 第一次来到自己的时候打印
    public static void morrisPre(Node head){
        if(head == null) return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 有左树的第一次
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");
                    cur = cur.left;
                    continue;
                }else { // 有左树的第一次
                    mostRight.right = null;
                }
            }else{
                // 没有左树的只有第一次
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }
    //遍历到第二次节点的时候 打印他的左树的右边界
    //左树的右边界逆序打印
    public static void morrisPos(Node head) {
        if (head == null) {
            return ;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node head) {
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        printTree(head);
        morrisIn(head);
        morrisPre(head);
        morrisPos(head);
        printTree(head);

    }


}
