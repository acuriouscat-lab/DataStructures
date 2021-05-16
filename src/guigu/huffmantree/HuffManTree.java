package guigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffManTree {


    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
         preOrder(createHuffManTree(arr));

    }

    /**
     *
     * @param arr   要转换为赫夫曼树的数组
     * @return  最后的节点
     */
    //创建Huffmantree的方法
    public static Node createHuffManTree(int[] arr) {
        //从小到大排序
        List<Node> list = new ArrayList<>();

        for (int value : arr) {
            list.add(new Node(value));
        }
        while (list.size()>1) {
            //排序
            Collections.sort(list);
           // System.out.println("list: " + list);

            //去除根节点权值最小的两颗二叉树
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            list.add(parentNode);
            list.remove(leftNode);
            list.remove(rightNode);

        }
        return list.get(0);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }else{
            System.out.println("无法遍历");
        }
    }


}


class Node implements Comparable<Node>{
    int value;  //权值
    Node left;
    Node right;


    //前序遍历
    public  void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}