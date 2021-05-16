package guigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 给出一个数列要求转化为一颗赫夫曼树
 *          步骤：
 *              1)从小到大排序 将每一个数据转化为一个节点 每个节点看成最简单的二叉树
 *              2)取出根节点权值最小的两颗二叉树
 *              3)组成一颗新的二叉树 新的二叉树的权值为前面两个二叉树权值之和
 *              4)再将这个新的二叉树 重新排序 重复1234 知道所有的到处理
 */
public class HuffManTreeDemo {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        preOreder(creatHuffManTree(arr));
    }

    //将数组转化为hefuumantree
    public static Nodes creatHuffManTree(int[] arr) {
        //将数组中的数据转化为一个节点
        ArrayList<Nodes> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Nodes(value));
        }
        //排序并组成新的二叉树
        while (nodes.size()>1) {
            Collections.sort(nodes);
            Nodes leftNode = nodes.get(0);
            Nodes rightNode = nodes.get(1);
            Nodes parentNode = new Nodes(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            nodes.add(parentNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }
        return nodes.get(0);
    }

    public static void preOreder(Nodes root) {
        if (root != null) {
            root.preOrder();
        }else{
            System.out.println("无法遍历");
        }
    }
}

//为了能够使用Collections的排序方法 实现Comparable接口
class Nodes implements Comparable<Nodes> {

    int value;  //权值
    Nodes left;
    Nodes right;

    //前序遍历方法
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    @Override
    public String toString() {
        return "Nodes{" +
                "value=" + value +
                '}';
    }

    public Nodes(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Nodes o) {
        return this.value - o.value;    //这样写为按value升序排序
    }
}
