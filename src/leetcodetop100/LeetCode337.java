package leetcodetop100;

public class LeetCode337 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Info {
        public int yes;
        public int no;

        public Info(int y, int n) {
            yes = y;
            no = n;
        }
    }


    public static int rob(TreeNode root) {
        Info process = process(root);
        return Math.max(process.yes, process.no);
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int yes = leftInfo.no + rightInfo.no + node.val;
        int no = Math.max(leftInfo.yes, leftInfo.no) + Math.max(rightInfo.yes, rightInfo.no);
        return new Info(yes, no);
    }

    public static int rob2(TreeNode root){
        int[] res = process1(root);
        return Math.max(res[0], res[1]);
    }

    public static int[] process1(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = process1(node.left);
        int[] right = process1(node.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = node.val + left[0] + right[0];
        return res;
    }

}
