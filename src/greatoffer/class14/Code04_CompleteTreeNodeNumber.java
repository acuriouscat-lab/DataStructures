package greatoffer.class14;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-11 15:54
 */
public class Code04_CompleteTreeNodeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 完全二叉树的节点个数
    public static int nodeNum(Node node) {
        if (node == null) {
            return 0;
        }
        return bs(node, 1, mostLeftLevel(node, 1));
    }

    // 当前在第 level 层，一共有 h 层
    public static int bs(Node node, int level, int h) {
        if (level == h) {
            return 1;
        }
        // 如果右边孩子达到了最后一层，因为又是完全二叉树，所以左树一定是满二叉树
        if (mostLeftLevel(node.right, level + 1) == h) {
            return (1 << (h - level)) + bs(node.right, level + 1, h);
        } else {
            return (1 << (h - level - 1)) + bs(node.left, level + 1, h);
        }
    }

    // 当前最左侧的深度
    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }


}
