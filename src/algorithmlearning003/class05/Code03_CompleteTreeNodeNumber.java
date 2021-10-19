package algorithmlearning003.class05;

public class Code03_CompleteTreeNodeNumber {

    //  求完全二叉树节点的个数
    //
    //    要求时间复杂度低于O(N)
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 请保证head为头的树，是完全二叉树
    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    // node在第level层，h是总的深度（h永远不变，全局变量
    // 以node为头的完全二叉树，节点个数是多少
    public static int bs(Node node,int level,int h){
        if(level == h){
            return 1;
        }
        if(mostLeftLevel(node.right,level + 1) == h){//说明以左子树为头的二叉树是满二叉树
            return (1 << (h - level)) + bs(node.right, level + 1, h);
        }else{//说明以右子树为头的二叉树是满二叉树，但是比左树少了一层
            return (1 << (h - level - 1)) + bs(node.left, level + 1, h);
        }
    }

    // 如果node在第level层，
    // 求以node为头的子树，最大深度是多少
    // node为头的子树，一定是完全二叉树
    public static int mostLeftLevel(Node head,int level){
        Node node = head;
        while(node != null){
            node = node.left;
            level++;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }



}
