package algorithmlearning.class07;

import java.util.Stack;

public class Code_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    //利用栈结构 弹出并打印当前节点，再压入右再压入左
    public static void pre(Node head) {
        System.out.println("pre-order:");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (stack.size() != 0) {
                head = stack.pop();
                System.out.print(head.value + "-");
                if (head.right != null) stack.push(head.right);
                if (head.left != null) stack.push(head.left);
            }
        }
        System.out.println();
    }

    //将所有子树都分为左头部分 左边处理玩了之后再处理右边
    public static void in(Node head) {
        System.out.println("in-order");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    //利用两个栈结构 头右左 --- >  左右头
    public static void pos1(Node head) {
        System.out.println("pos-order:");
        if (head != null) {
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.add(head);
            while (stack1.size() != 0) {
                head = stack1.pop();
                stack2.push(head);
                if (head.left != null) stack1.push(head.left);
                if (head.right != null) stack1.push(head.right);
            }

            while (stack2.size() != 0) {
                System.out.print(stack2.pop().value + " ");
            }
        }
        System.out.println();
    }

    public static void pos2(Node head) {
        System.out.println("pos-order");
        if (head != null) {
            Node h = head;//指向上一个处理的节点  标记左右节点是否处理完了
            Node c = null;
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {//判断左孩子是否处理了
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {//判断右孩子处理了嘛
                    stack.push(c.right);
                }else{
                    System.out.print(stack.pop().value + " ");//都处理了就该你了
                    h = c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }

}
