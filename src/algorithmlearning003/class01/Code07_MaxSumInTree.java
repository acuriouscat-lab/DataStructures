package algorithmlearning003.class01;

public class Code07_MaxSumInTree {
    //给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：
    //1）路径必须是头节点出发，到叶节点为止，返回最大路径和
    //2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
    //3）路径可以从任何节点出发，到任何节点，返回最大路径和
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

    public static int maxSum = Integer.MIN_VALUE;

    //1）路径必须是头节点出发，到叶节点为止，返回最大路径和
    public static int maxPath(Node head) {
        maxSum = Integer.MIN_VALUE;
        p(head, 0);
        return maxSum;
    }

    public static void p(Node x, int sum) {
        if (x.left == null && x.right == null) {
            maxSum = Math.max(maxSum, x.value + sum);
            return;
        }
        if (x.left != null) {
            p(x.left, sum + x.value);
        }
        if (x.right != null) {
            p(x.right, sum + x.value);
        }
    }


    public static int maxDis(Node head) {
        if (head == null) {
            return 0;
        }
        return process2(head);
    }

    private static int process2(Node head) {
        if (head.left == null && head.right == null) {
            return head.value;
        }
        int next = Integer.MIN_VALUE;
        if (head.left != null) {
            next = process2(head.left);
        }
        if (head.right != null) {
            next = Math.max(process2(head.right), next);
        }
        return next + head.value;
    }

    //2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
    public static int maxSum2(Node head) {
        if (head == null) {
            return 0;
        }
        return process3(head).allTreeMaxSum;
    }

    // 1）X无关的时候， 1， 左树上的整体最大路径和 2， 右树上的整体最大路径和
    // 2) X有关的时候 3， x自己 4， x往左走 5，x往右走
    public static Info process3(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process3(x.left);
        Info rightInfo = process3(x.right);
        int p1 = Integer.MIN_VALUE;
        int p2 = Integer.MIN_VALUE;
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        int p5 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
            p2 = leftInfo.fromHeadMaxSum + p3;
        }
        if (rightInfo != null) {
            p4 = rightInfo.allTreeMaxSum;
            p5 = rightInfo.fromHeadMaxSum + p3;
        }
        int all = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5));
        int from = Math.max(Math.max(p3, p2), p5);
        return new Info(all, from);

    }

    public static class Info {
        public int allTreeMaxSum;
        public int fromHeadMaxSum;

        public Info(int allTreeMaxSum, int fromHeadMaxSum) {
            this.allTreeMaxSum = allTreeMaxSum;
            this.fromHeadMaxSum = fromHeadMaxSum;
        }
    }


    //3）路径可以从任何节点出发，到任何节点，返回最大路径和
    // 1）X无关的时候， 1， 左树上的整体最大路径和 2， 右树上的整体最大路径和
    // 2) X有关的时候 3， x自己 4， x往左走 5，x往右走 6, 既往左，又往右
    public static Info f3(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = f3(x.left);
        Info rightInfo = f3(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allTreeMaxSum;
        }
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = x.value + leftInfo.fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = x.value + rightInfo.fromHeadMaxSum;
        }

        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null) {
            p6 = x.value + leftInfo.fromHeadMaxSum + rightInfo.fromHeadMaxSum;
        }

        int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(Math.max(p4, p5), p6));
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);
        return new Info(allTreeMaxSum, fromHeadMaxSum);
    }

    public static int max = Integer.MIN_VALUE;

    public static int bigShuai(Node head) {
        if (head.left == null && head.right == null) {
            max = Math.max(max, head.value);
            return head.value;
        }
        int nextMax = 0;
        if (head.left != null) {
            nextMax = bigShuai(head.left);
        }
        if (head.right != null) {
            nextMax = Math.max(nextMax, bigShuai(head.right));
        }
        int ans = head.value + nextMax;
        max = Math.max(max, ans);
        return ans;
    }


}
