package greatoffer.class07;

public class Code02_MinCameraCover {

    // 本题测试链接 : https://leetcode.com/problems/binary-tree-cameras/

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
    }

    public static int minCameraCover1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info data = process(root);
        return Math.min(data.uncovered + 1, Math.min(data.coveredHasCamera, data.coveredNoCamera));
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int uncovered = left.coveredNoCamera + right.coveredNoCamera;
        int coveredHasCamera = Math.min(left.uncovered, left.coveredNoCamera) + Math.min(right.uncovered, right.coveredNoCamera) + 1;
        int coveredNoCamera = Math.min(left.coveredHasCamera +
                right.coveredHasCamera, Math.min(left.coveredHasCamera +
                right.coveredNoCamera, left.coveredNoCamera + right.coveredHasCamera));
        return new Info(uncovered, coveredHasCamera, coveredNoCamera);
    }

    public static class Info {
        public int uncovered;
        public int coveredHasCamera;
        public int coveredNoCamera;

        public Info(int uc, int chc, int cnc) {
            uncovered = uc;
            coveredHasCamera = chc;
            coveredNoCamera = cnc;
        }
    }

    public static int minCameraCover2(TreeNode root) {
        Data data = process2(root);
        return data.cameras + (data.status == Status.UNCOVERED ? 1 : 0);
    }

    // 以x为头，x下方的节点都是被covered，x自己的状况，分三种
    public static enum Status {
        UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA
    }

    // 以x为头，x下方的节点都是被covered，得到的最优解中：
    // x是什么状态，在这种状态下，需要至少几个相机
    public static class Data {
        public Status status;
        public int cameras;

        public Data(Status status, int cameras) {
            this.status = status;
            this.cameras = cameras;
        }
    }

    public static Data process2(TreeNode X) {
        if (X == null) {
            return new Data(Status.COVERED_NO_CAMERA, 0);
        }
        Data left = process2(X.left);
        Data right = process2(X.right);
        int cameras = left.cameras + right.cameras;

        // 左、或右，哪怕有一个没覆盖
        if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
            return new Data(Status.COVERED_HAS_CAMERA, cameras + 1);
        }

        // 左右孩子，不存在没被覆盖的情况
        if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
            return new Data(Status.COVERED_NO_CAMERA, cameras);
        }
        // 左右孩子，不存在没被覆盖的情况，也都没有相机
        return new Data(Status.UNCOVERED, cameras);
    }

}
