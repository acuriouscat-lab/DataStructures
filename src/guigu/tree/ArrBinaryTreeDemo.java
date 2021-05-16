package guigu.tree;

/**
 *   给出一个数组   要求以二叉树前序遍历的方式进行遍历，  前序遍历的结果应当为1245367
 *
 *      顺序二叉树的特点：
 *                  1）:顺序二叉树通常只考虑完全二叉树
 *                  2):第N个元素的左子节点为2*n+1
 *                  3)：第n个元素的右子节点为2*n+2
 *                  4):第n个元素的父子节点为（n-1）/2
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        ArrBinaryTree tree = new ArrBinaryTree(arr);

       // guigu.tree.preOrder();

       // guigu.tree.infixOrder(0); //4251637
        tree.postOrder(0);  //4526731
    }




}

class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //方法重载
    public void preOrder(){
        this.preOrder(0);
    }

    /**
     *  前序遍历
     * @param index 数组的下标
     */
    public void preOrder(int index) {

        if (arr == null || arr.length == 0 || index < 0 || index >= arr.length) {
            System.out.println("无法遍历");
            return;
        }

        //输出当前节点
        System.out.print(arr[index]+" ");

        //向左递归
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }

        //向右递归
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }


    }

    /**
     *  中序遍历
     * @param index 数组的下表
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0 || index < 0 || index >= arr.length) {
            throw new RuntimeException("无法遍历");
        }
        //向左递归
        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        //向右递归
        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    public void postOrder(int index) {
        if (arr == null || arr.length == 0 || index < 0 || index >= arr.length) {
            return;
        }
        //向左递归
        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        //向右递归
        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }
}
